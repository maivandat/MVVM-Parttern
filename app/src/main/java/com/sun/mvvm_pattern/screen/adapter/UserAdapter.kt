package com.sun.mvvm_pattern.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sun.mvvm_pattern.R
import com.sun.mvvm_pattern.data.model.User
import com.sun.mvvm_pattern.databinding.LayoutItemUserBinding
import com.sun.mvvm_pattern.screen.viewmodel.ItemUserViewModel
import com.sun.mvvm_pattern.utils.OnRecyclerViewItemListener
import kotlinx.android.synthetic.main.layout_item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val userList = mutableListOf<User>()
    private var onRecyclerViewItemListener: OnRecyclerViewItemListener<User>? = null
    fun updateData(users: MutableList<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnRecyclerViewItemListener(listener: OnRecyclerViewItemListener<User>?) {
        onRecyclerViewItemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<LayoutItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_user,
                parent,
                false
            ), onRecyclerViewItemListener
        )
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if ((position % 2) == 0) {
            holder.itemView.containerItem.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_fade_in)
        } else {
            holder.itemView.containerItem.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_fade_out)
        }
        holder.getData(userList[position])
    }

    inner class ViewHolder(
        private val binding: ViewDataBinding,
        private val listener: OnRecyclerViewItemListener<User>?
    ) : RecyclerView.ViewHolder(binding.root) {
        private var user: User? = null

        fun getData(data: User) {
            if (binding is LayoutItemUserBinding) {
                binding.viewModel = ItemUserViewModel(listener)
                binding.viewModel?.setData(data)
                binding.executePendingBindings()
            }
        }
    }
}
