package com.sun.mvvm_pattern.screen.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.mvvm_pattern.BR
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.utils.OnRecyclerViewItemListener

class ItemUserViewModel(
    private val listener: OnRecyclerViewItemListener<User>?
): BaseObservable() {

    @Bindable
    var user: User? = null

    fun setData(data: User?) {
        data?.let {
            user = it
            notifyPropertyChanged(BR.user)
        }
    }

    fun onClickListener() {user?.let { listener?.onItemCLickListener(it) }}
}