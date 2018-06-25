package com.droidmob.zohousers.view.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.droidmob.zohousers.viewmodel.BaseViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected val viewModel: VM by lazy { initialiseViewModel() }
    lateinit var viewDataBinding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, setupLayoutRes())
        viewModel.onCreate(intent.extras)
        onViewModelCreated()
    }



    @LayoutRes
    abstract fun setupLayoutRes():Int

    abstract fun initialiseViewModel(): VM

    abstract fun onViewModelCreated()
}