package com.phucth.base.usecase

import com.phucth.base.model.ResultModelUi
import kotlinx.coroutines.flow.Flow


interface UseCaseBase<T, X> {
    suspend fun execute(paramRequest: T? = null): Flow<ResultModelUi<X>>
}