package com.example.spinningwheel

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.spinningwheel.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val random = Random()

        // on click listener for btnSpin
        activityMainBinding.btnSpin.setOnClickListener(View.OnClickListener {
            // disabling the button so that user
            // should not click on the button
            // while the wheel is spinning
            activityMainBinding.btnSpin.isEnabled = false

            // reading random value between 10 to 30
            var spin = random.nextInt(20) + 10

            // since the wheel has 10 divisions, the
            // rotation should be a multiple of
            // 360/10 = 36 degrees
            spin *= 36

            // timer for each degree movement
            var timer = object : CountDownTimer((spin).toLong(), 1) {
                override fun onTick(l: Long) {
                    // rotate the wheel
                    val rotation = activityMainBinding.ivWheel.rotation.plus(2)
                    activityMainBinding.ivWheel.rotation = rotation
                    Log.d("MainActivity","Value: $l")
                }

                override fun onFinish() {
                    activityMainBinding.btnSpin.isEnabled = true
                    showInviteDialog()
                }
            }.start()
        })
    }

    private fun showInviteDialog(){
        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setView(R.layout.invite_popup)
        dialog.setCancelable(true)
        dialog.show()
    }
}