package com.sun.mvvm_pattern.screen.fragment.user

import androidx.lifecycle.MutableLiveData
import com.sun.mvvm_pattern.data.model.User

interface UserNavigation {
    fun onGetDataSuccess(userList: MutableLiveData<MutableList<User>>)
    fun onInsertUserSuccess()
    fun onDeleteUserSuccess()
    fun onError(throwable: Throwable)
}