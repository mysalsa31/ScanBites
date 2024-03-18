package com.example.scanbite_v1_camera_function.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome To The ScanBite Application"
    }
    val text: LiveData<String> = _text
}