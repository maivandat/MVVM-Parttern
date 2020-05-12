package com.sun.mvvm_pattern.data.source.remote.api

import com.sun.mvvm_pattern.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    fun getUsers(): Observable<List<User>>
}