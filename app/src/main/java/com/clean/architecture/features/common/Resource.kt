package com.clean.architecture.features.common

/**
 * Created by Rehan Sarwar on 11/06/2022.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
