package com.khalifa.github.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khalifa.github.databinding.UserItemBinding
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.presentation.util.loadUrl

class UsersAdapter(private val onItemClick: (UserDomainEntities.UserDomainItem) -> Unit) :
    PagingDataAdapter<UserDomainEntities.UserDomainItem, UsersAdapter.UserViewHolder>(
        UserDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(holder.binding, it)
        }
    }

    inner class UserViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            binding: UserItemBinding,
            user: UserDomainEntities.UserDomainItem,
        ) {
            with(binding) {
                userNameTextView.text = user.userName
                userAvatarImageView.loadUrl(user.avatarUrl)
            }

            itemView.setOnClickListener {
                onItemClick(user)
            }
        }
    }


    class UserDiffCallback : DiffUtil.ItemCallback<UserDomainEntities.UserDomainItem>() {
        override fun areItemsTheSame(
            oldItem: UserDomainEntities.UserDomainItem,
            newItem: UserDomainEntities.UserDomainItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UserDomainEntities.UserDomainItem,
            newItem: UserDomainEntities.UserDomainItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
