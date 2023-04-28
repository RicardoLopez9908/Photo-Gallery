package com.example.photogallery.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.photogallery.util.Response
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    var screenContent = MutableStateFlow<Response<Any?>>(Response.Success(null))
        protected set

    protected val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, t ->
            screenContent.tryEmit(Response.Error(Exception(t)))
        }
}