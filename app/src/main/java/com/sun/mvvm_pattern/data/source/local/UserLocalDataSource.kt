package com.sun.mvvm_pattern.data.source.local

import android.content.Context
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.UserDataSource
import com.sun.mvvm_pattern.data.source.local.database.UserDatabase
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalDataSource(private val context: Context) : UserDataSource.Local {

    private val userDao = UserDatabase.getInstance(context).userDao()
    companion object {
        private var INSTANCE: UserLocalDataSource? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UserLocalDataSource(context).also { INSTANCE = it }
        }
    }

    override fun getUserList(): Single<List<User>> {
        return userDao.getUsers()
    }

    override fun insertUser(vararg user: User): Completable {
        return userDao.insertUser(*user)
    }

    override fun deleteUsers(vararg user: User): Completable {
        return userDao.deleteUsers(*user)
    }
}