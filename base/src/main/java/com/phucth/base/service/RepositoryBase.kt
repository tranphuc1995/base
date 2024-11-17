package com.phucth.base.service

import com.phucth.base.model.ErrorMessageApi
import com.phucth.base.model.ResultNetworkApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class RepositoryBase {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> Response<T>
    ): Flow<ResultNetworkApi<T>> {
        return flow {
            val resultNetworkApi = ResultNetworkApi<T>()
            val errorMessageApi = ErrorMessageApi()
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    resultNetworkApi.modelResponseFromApi = data
                } else {
                    val error = response.errorBody()
                    if (error != null) {
                        errorMessageApi.message = error.toString()
                    } else {
                        errorMessageApi.message = "error response is null"
                    }
                }
            } else {
                errorMessageApi.message = response.errorBody()?.string() ?: "Unknown error"
                resultNetworkApi.error = errorMessageApi
            }
            emit(resultNetworkApi)
        }.catch { e ->
            e.printStackTrace()
            emit(ResultNetworkApi())
        }.flowOn(dispatcher)
    }
}