package com.example.youtube.network


data class Resource<out T>(val status: Status, val data: T?, val msg: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg = msg)
        }
        fun <T> loading():Resource<T>{
            return Resource(Status.LOADING,null, null)
        }

    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}


