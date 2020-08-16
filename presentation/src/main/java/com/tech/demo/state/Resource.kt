package com.tech.demo.state

class Resource<T> constructor(val status: ResourceState,
                                  val data: T?,
                                  val message: String?) {

    fun  success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun error(message: String?): Resource<T> {
        return Resource(ResourceState.ERROR, null, message)
    }

    fun loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

}