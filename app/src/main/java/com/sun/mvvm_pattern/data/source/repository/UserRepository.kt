package com.sun.mvvm_pattern.data.source.repository

import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.UserDataSource
import com.sun.mvvm_pattern.data.source.local.UserLocalDataSource
import com.sun.mvvm_pattern.data.source.remote.UserRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserRepository(
    private val local: UserLocalDataSource,
    private val remote: UserRemoteDataSource
) : UserDataSource.Local, UserDataSource.Remote {

    override fun getUsers(): Observable<List<User>>? {
        return remote.getUsers()
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun getInstance(
            local: UserLocalDataSource,
            remote: UserRemoteDataSource
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UserRepository(local, remote).also { INSTANCE = it }
        }
    }

    override fun getUserList(): Single<List<User>> {
        return local.getUserList()
    }

    override fun insertUser(vararg user: User): Completable {
        return local.insertUser(*user)
    }

    override fun deleteUsers(vararg user: User): Completable {
        return local.deleteUsers(*user)
    }
}