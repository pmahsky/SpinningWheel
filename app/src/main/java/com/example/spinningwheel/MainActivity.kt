package com.example.spinningwheel

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.spinningwheel.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDatBinding()
        initObservers()
    }

    private fun initDatBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        activityMainBinding.viewModel = viewModel
        activityMainBinding.lifecycleOwner = this
    }

    private fun initObservers() {
        viewModel.getOnSpinClickLiveData().observe(this, androidx.lifecycle.Observer {
            rotateWheel()
        })
    }

    private fun rotateWheel() {
        val spin = viewModel.getSpinValue()
        // disabling the button so that user
        // should not click on the button
        // while the wheel is spinning
        activityMainBinding.btnSpin.isEnabled = false
        // timer for each degree movement
        object : CountDownTimer((spin*20).toLong(), 1) {
            override fun onTick(l: Long) {
                Log.d("MainActivity","Wheel Rotation: ${activityMainBinding.ivWheel.rotation}")
                // rotate the wheel
                val rotation = activityMainBinding.ivWheel.rotation.plus(2)

                activityMainBinding.ivWheel.rotation = rotation
                Log.d("MainActivity","onTick: $l")
            }

            override fun onFinish() {
                activityMainBinding.btnSpin.isEnabled = true
                showInviteDialog()
            }
        }.start()
    }

    private fun showInviteDialog(){
        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setView(R.layout.invite_popup)
        dialog.setCancelable(true)
        dialog.show()
    }
}