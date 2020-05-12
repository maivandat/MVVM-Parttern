package com.sun.mvvm_pattern.data.source

import com.sun.mvvm_pattern.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserDataSource {
    interface Local {
        fun getUserList(): Single<List<User>>
        fun insertUser(vararg user: User): Completable
        fun deleteUsers(vararg user: User): Completable
    }

    interface Remote {
        fun getUsers(): Observable<List<User>>?
    }
}

