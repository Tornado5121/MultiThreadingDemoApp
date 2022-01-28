package com.natife.example.multithreadingdemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay

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

    suspend fun executeRandomNumberCycleByCoroutines() {
        while (i < 10) {
            val data = Math.random()
            mLiveData.postValue(data)
            delay(1000)
            i++
        }
    }

    fun executeRandomNumberCycleByRxJava(): Observable<Double> {
        return Observable.create { subscriber ->
                while (i < 10) {
                    val data = Math.random()
                    subscriber.onNext(data)
                    Thread.sleep(1000)
                    i++
                }
        }
    }

}

