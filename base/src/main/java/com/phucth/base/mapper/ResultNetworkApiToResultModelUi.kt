package com.phucth.base.mapper

import com.phucth.base.model.ResultModelUi
import com.phucth.base.model.ResultNetworkApi

inline fun <T, X> ResultNetworkApi<T>.mapToModelUi(
    functionMap: (T?) -> X
): ResultModelUi<X> {
    if (this.error != null) {
        return ResultModelUi(modelUi = null, errorMessageApi = this.error)
    } else {
        val modelUi = functionMap(this.modelResponseFromApi)
        return ResultModelUi(modelUi = modelUi, errorMessageApi = null)
    }
}