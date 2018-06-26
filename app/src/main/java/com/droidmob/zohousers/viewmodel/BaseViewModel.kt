package com.droidmob.zohousers.viewmodel

import android.arch.lifecycle.ViewModel
import android.os.Bundle

abstract class BaseViewModel : ViewModel() {

    protected val TAG by lazy { javaClass.simpleName }

    abstract fun onCreate(bundle: Bundle?)

}