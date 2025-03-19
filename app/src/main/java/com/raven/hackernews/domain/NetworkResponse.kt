package com.raven.hackernews.domain


sealed class NetworkResponse<out T> {
    data object NotInitialized : NetworkResponse<Nothing>()
    data object IsLoading : NetworkResponse<Nothing>()
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: Exception) : NetworkResponse<Nothing>()
}