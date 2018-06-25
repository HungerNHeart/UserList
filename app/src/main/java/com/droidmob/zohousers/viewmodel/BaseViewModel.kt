package com.droidmob.zohousers.viewmodel

import android.arch.lifecycle.ViewModel
import android.os.Bundle

abstract class BaseViewModel : ViewModel() {

    abstract fun onCreate(bundle: Bundle?)

}