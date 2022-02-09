package com.natife.example.multithreadingdemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private var i = 0
    private val mLiveData = MutableLiveData<Double>()
    val liveData: LiveData<Double> = mLiveData

    fun executeRandomNumberCycleByLiveData() {
        Thread {
            while (i < 10) {
                val data = Math.random()
                mLiveData.postValue(data)
                Thread.sleep(1000)
                i++
            }
        }.start()
    }

    fun executeRandomNumberCycleByCoroutines() {
        viewModelScope.launch {
            while (i < 10) {
                val data = Math.random()
                mLiveData.postValue(data)
                delay(1000)
                i++
            }
        }
    }

    fun executeRandomNumberCycleByRxJava(): Observable<Double> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .map {
                Math.random()
            }.take(10)
    }

    override fun onCleared() {
        super.onCleared()
        Thread.interrupted()
    }

}

