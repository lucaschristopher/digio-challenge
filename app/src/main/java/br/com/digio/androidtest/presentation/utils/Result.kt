package br.com.digio.androidtest.presentation.utils

internal sealed class Result<out T> {
    data object Initial : Result<Nothing>()
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val message: String?, val data: T? = null) : Result<T>()
}
