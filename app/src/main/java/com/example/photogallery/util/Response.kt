package com.example.photogallery.util

import java.lang.Exception

sealed class Response<out R> {
    object Loading : Response<Nothing>()
    data class Success<out T>(val data: T?) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}
