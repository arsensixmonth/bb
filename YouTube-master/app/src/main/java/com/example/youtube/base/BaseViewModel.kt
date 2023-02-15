package com.example.youtube.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {
    public val loading : MutableLiveData<Boolean> = MutableLiveData()
}