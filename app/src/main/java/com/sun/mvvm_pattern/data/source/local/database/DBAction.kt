package com.sun.mvvm_pattern.data.source.local.database

import com.sun.mvvm_pattern.data.model.User

interface DBAction {
    fun add()
    fun update()
    fun delete()
    fun deleteAll()
    fun getList(): List<User>
}
