package com.example.spinningwheel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

    private val onSpinClickLiveData: MutableLiveData<Unit> = MutableLiveData()

    fun onSpinClicked(v: View) {
        onSpinClickLiveData.postValue(Unit)
    }

    fun getOnSpinClickLiveData(): MutableLiveData<Unit> {
        return onSpinClickLiveData
    }

    fun getSpinValue(): Int {
        val random = Random()
        // getting some random value between 10 and 30
        var spin = random.nextInt(20) + 10
        // spin value can be returned as multiple of 36 degrees(360/10) as the wheel has 10 divisions
        spin *= 36
        return spin
    }
}