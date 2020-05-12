package com.sun.mvvm_pattern.screen.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sun.mvvm_pattern.R
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.data.source.local.UserLocalDataSource
import com.sun.mvvm_pattern.data.source.remote.UserRemoteDataSource
import com.sun.mvvm_pattern.data.source.remote.connection.RetrofitClient
import com.sun.mvvm_pattern.data.source.repository.UserRepository
import com.sun.mvvm_pattern.screen.adapter.UserAdapter
import com.sun.mvvm_pattern.utils.OnRecyclerViewItemListener
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment(), UserNavigation, OnRecyclerViewItemListener<User> {
    private var repository: UserRepository? = null
    private val adapter = UserAdapter()
    private lateinit var factoryUser: UserViewModelFactory
    private var viewModel: UserViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerViewUsers.adapter = adapter
        adapter.setOnRecyclerViewItemListener(this)
        recyclerViewUsers.itemAnimator = SlideInLeftAnimator()
    }

    private fun initData() {
        context?.let {
            repository = UserRepository(
                UserLocalDataSource.getInstance(it.applicationContext),
                UserRemoteDataSource.getInstance(RetrofitClient.instance)
            )
        }
        repository?.let {
            factoryUser = UserViewModelFactory(it, this)
        }
        viewModel = ViewModelProviders.of(this, factoryUser).get(UserViewModel::class.java)
        viewModel?.getUsers()
    }

    companion object {
        private var INSTANCE: UserFragment? = null

        fun getInstance(): UserFragment? {
            return INSTANCE ?: UserFragment()
        }
    }

    override fun onGetDataSuccess(userList: MutableLiveData<MutableList<User>>) {
        userList.observe(this,
            Observer<MutableList<User>> {
                adapter.updateData(it)
            }
        )
    }

    override fun onInsertUserSuccess() {
        Toast.makeText(context, "Insert data to database success", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteUserSuccess() {
        Toast.makeText(context, "Delete data from the database", Toast.LENGTH_SHORT).show()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemCLickListener(data: User?) {
        Toast.makeText(context, data?.login, Toast.LENGTH_SHORT).show()
    }
}

