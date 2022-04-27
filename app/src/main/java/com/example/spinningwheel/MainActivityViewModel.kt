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

        // reading random value between 10 to 30
        var spin = random.nextInt(20) + 10
        Log.d("MainActivity", "Spin 1=== $spin")
        // since the wheel has 10 divisions, the
        // rotation should be a multiple of
        // 360/10 = 36 degrees
        spin *= 36

        Log.d("MainActivity", "Spin 2=== $spin")

        return spin
    }
}