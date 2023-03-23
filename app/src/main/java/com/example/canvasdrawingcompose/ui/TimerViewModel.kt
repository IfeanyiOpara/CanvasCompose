package com.example.canvasdrawingcompose.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    val timeState = MutableLiveData<Int>()

    fun startTime(numberOfRewards: Int){
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            var i = 0
            while (i < numberOfRewards){
                delay(500)
                timeState.postValue(i)
                i++
            }
        }
    }

}