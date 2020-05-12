package com.sun.mvvm_pattern.screen.fragment.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel constructor(
    private val repository: UserRepository?,
    private val navigation: UserNavigation
) : ViewModel() {
    private val userList = MutableLiveData<MutableList<User>>()
    private val compositeDisposable = CompositeDisposable()

    fun getUsers() {
        val disposable = repository?.getUsers()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                userList.value = it as MutableList<User>
                navigation.onGetDataSuccess(userList)
                insertUserDB(users = *it.toTypedArray())
            }, {
                navigation.onError(it)
                getUserListDB()
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    private fun insertUserDB(vararg users: User) {
        val disposable = repository?.insertUser(*users)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
               navigation.onInsertUserSuccess()
            },{
                navigation.onError(it)
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    private fun getUserListDB() {
        val disposable =
        repository?.getUserList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                userList.value = it as MutableList<User>
                navigation.onGetDataSuccess(userList)
            }, {
                navigation.onError(it)
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    fun deleteUser(vararg user: User) {
        val disposable = repository?.deleteUsers(*user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                navigation.onDeleteUserSuccess()
                getUserListDB()
            }, {
                navigation.onError(it)
            })
        disposable?.let { compositeDisposable.add(it) }
    }
}
