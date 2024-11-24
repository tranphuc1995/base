package com.phucth.base.model

sealed class StateScreen<out T> {
    data object Loading : StateScreen<Nothing>()
    data class DataScreen<T>(val data: T) : StateScreen<T>()
    data class Error(val messageApi: ErrorMessageApi) : StateScreen<Nothing>()
}