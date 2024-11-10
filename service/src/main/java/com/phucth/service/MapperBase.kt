package com.phucth.service

interface MapperBase {
    fun <modelApi, modelUI> mapFromModelApiToModeUI(data : modelApi): modelUI
}