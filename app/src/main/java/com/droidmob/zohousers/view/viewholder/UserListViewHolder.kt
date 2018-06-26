package com.droidmob.zohousers.view.viewholder

import com.droidmob.zohousers.databinding.InflaterUserListItemBinding
import com.droidmob.zohousers.repository.dto.common.UserData

class UserListViewHolder(viewDataBinding: InflaterUserListItemBinding?) : BaseViewHolder<UserData, InflaterUserListItemBinding>(viewDataBinding) {

    override fun populateData(data: UserData?) {
        viewDataBinding?.setUserData(data)
    }
}