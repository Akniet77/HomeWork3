package com.example.homework3.core.network

data class Resourse<T>(val status: Status, val data : T?, val message: String?){

    enum class Status{
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{
        fun <T> success(data  : T?): Resourse<T>{
            return Resourse(Status.SUCCESS, data, null)
        }
        fun <T> loading(): Resourse<T>{
            return Resourse(Status.LOADING, null, null)

        }
        fun <T> error(msg: String?, data: T?): Resourse<T>{
            return Resourse(Status.ERROR, data, msg)

        }
    }
}