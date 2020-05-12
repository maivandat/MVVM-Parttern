package com.sun.mvvm_pattern.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.local.database.dao.UserDao

@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "user_db.db"
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context) = INSTANCE
            ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}
