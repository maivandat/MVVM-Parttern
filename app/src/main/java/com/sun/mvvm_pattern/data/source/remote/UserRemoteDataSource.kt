package com.sun.mvvm_pattern.data.source.remote

import io.reactivex.Observable
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.UserDataSource
import com.sun.mvvm_pattern.data.source.remote.connection.RetrofitClient

class UserRemoteDataSource(private val retrofitClient: RetrofitClient) : UserDataSource.Remote {

    private val userService = retrofitClient.getUserService()
    companion object {
        private var INSTANCE: UserRemoteDataSource? = null

        fun getInstance(retrofitClient: RetrofitClient) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UserRemoteDataSource(retrofitClient).also { INSTANCE = it }
        }
    }

    override fun getUsers(): Observable<List<User>>? {
        return userService?.getUsers()
    }
}