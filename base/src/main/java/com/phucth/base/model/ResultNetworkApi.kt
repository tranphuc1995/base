package com.phucth.base.model

data class ResultNetworkApi<T>(
    var modelResponseFromApi: T? = null,
    var error: ErrorMessageApi? = null
)