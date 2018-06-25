package com.droidmob.zohousers.view.viewholder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder<T, VB : ViewDataBinding>(protected val viewDataBinding: VB?) : RecyclerView.ViewHolder(viewDataBinding?.root) {
    var data: T? = null
        set(value) {
            field = value
            populateData(value)
        }

    abstract fun populateData(data: T?)
}