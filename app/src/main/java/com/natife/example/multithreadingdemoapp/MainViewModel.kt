package com.natife.example.multithreadingdemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var i = 0
    private val mLiveData = MutableLiveData<Double>()
    val liveData: LiveData<Double> = mLiveData

    fun executeRandomNumberCycle() {
        Thread {
            while (i < 10) {
                val data = getRandomNumber()
                mLiveData.postValue(data)
                Thread.sleep(1000)
                i++
            }
        }.start()
    }

    private fun getRandomNumber(): Double {
        return Math.random()
    }

}