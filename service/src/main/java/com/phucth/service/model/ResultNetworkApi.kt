package com.phucth.service.model

data class ResultNetworkApi<T>(
    var modelResponseFromApi: T? = null,
    val error: ErrorMessageApi? = null
)

fun <T, X> ResultNetworkApi<T>.mapToModelUi(modelApi: T, map: (T) -> X): X {
    return map(modelApi)
}