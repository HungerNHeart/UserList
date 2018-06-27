package com.droidmob.zohousers.view.adapter

import android.arch.paging.AsyncPagedListDiffer
import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.droidmob.zohousers.R
import com.droidmob.zohousers.repository.dto.common.UserData
import com.droidmob.zohousers.view.viewholder.UserListViewHolder


class UserListAdapter : PagedListAdapter<UserData, UserListViewHolder>(USER_COMPARATOR) {

    private val mDiffer = AsyncPagedListDiffer(this, USER_COMPARATOR)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
                DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.inflater_user_list_item, parent, false)))
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.data = mDiffer.getItem(position)
    }

    override fun submitList(pagedList: PagedList<UserData>?) {
        mDiffer.submitList(pagedList)
    }

    override fun getItemCount(): Int {
        return mDiffer.itemCount
    }


    companion object {
        val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserData>() {
            override fun areContentsTheSame(oldUser: UserData, newUser: UserData): Boolean =
                    oldUser == newUser

            override fun areItemsTheSame(oldUser: UserData, newUser: UserData): Boolean =
                    oldUser.id.equals(newUser.id)
        }
    }

}