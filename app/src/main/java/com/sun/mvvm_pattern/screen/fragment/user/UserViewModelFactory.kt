package com.sun.mvvm_pattern.screen.fragment.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sun.mvvm_pattern.data.source.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val repository: UserRepository,
    private val navigation: UserNavigation
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository, navigation) as T
    }
}