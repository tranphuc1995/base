package com.phucth.service.model

data class ResultNetworkApi<T>(
    var modelResponseFromApi: T? = null,
    val error: ErrorMessageApi? = null
)