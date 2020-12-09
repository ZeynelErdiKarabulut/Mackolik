package com.zeynelerdi.mackolik.data.remote.service

import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger

import org.json.JSONException

import java.net.ConnectException

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class ExceptionHandle {

    companion object {
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        var errorMsg = "Request unsuccessful"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException) {
                Logger.e("TAG", "Timeout: " + e.message)
                errorMsg = "timeout"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is ConnectException) {
                Logger.e("TAG", "No Connect: " + e.message)
                errorMsg = "No Connect"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                Logger.e("TAG", "Parse Exception: " + e.message)
                errorMsg = "Parse Exception"
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {//sunucu tarafındaki hata için
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e("TAG", " UnknownHostException: " + e.message)
                errorMsg = " UnknownHostException"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "IllegalArgumentException"
                errorCode = ErrorStatus.SERVER_ERROR
            } else {
                try {
                    Logger.e("TAG", "Message: " + e.message)
                } catch (e1: Exception) {
                    Logger.e("TAG", "Unknown Exception")
                }

                errorMsg = "Unknown Exception"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }

    }

}
