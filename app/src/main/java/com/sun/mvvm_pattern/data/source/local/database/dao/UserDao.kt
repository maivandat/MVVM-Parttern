package com.sun.mvvm_pattern.data.source.local.database.dao

import androidx.room.*
import com.sun.mvvm_pattern.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg users: User): Completable

    @Delete()
    fun deleteUsers(vararg users: User): Completable
}