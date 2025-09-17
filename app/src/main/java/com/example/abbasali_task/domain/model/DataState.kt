package com.example.abbasali_task.domain.model

sealed class DataState<out T> {
    data class Success<out T>(val response: T) : DataState<T>()
    data class Error(val errorMessage:String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}