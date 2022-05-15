package com.aoliva.metmuseum.common.model

sealed class LoadingErrorSuccess<T> {
    class Loading<T> : LoadingErrorSuccess<T>()
    class Error<T> : LoadingErrorSuccess<T>()
    class Success<T>(val data: T) : LoadingErrorSuccess<T>()
}