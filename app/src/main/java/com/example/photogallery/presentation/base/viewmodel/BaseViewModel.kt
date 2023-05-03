package com.example.photogallery.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.photogallery.util.Response
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _screenContent = MutableStateFlow<Response<Any?>>(Response.Success(null))
    val screenContent = _screenContent.asStateFlow()

    protected val contextAndErrorHandler =
        Dispatchers.Main + CoroutineExceptionHandler { _, t ->
            _screenContent.tryEmit(Response.Error(Exception(t)))
        }
}
