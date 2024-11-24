package com.phucth.base.model

sealed class StateScreen<T> {
    data object Loading : StateScreen<Nothing>()
    data class DataScreen<T>(val data: T) : StateScreen<T>()
    data class Error(val messageApi: ErrorMessageApi) : StateScreen<Nothing>()
}