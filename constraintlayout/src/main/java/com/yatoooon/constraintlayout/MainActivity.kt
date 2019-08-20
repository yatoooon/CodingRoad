package com.yatoooon.constraintlayout

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val set = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bias_chainstyle)


        //activity_bias_chainstyle下的
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintlayout)
        set.isForceId = false
        set.clone(constraintLayout)
        set.setHorizontalChainStyle(R.id.sbBtn, 1)
        TransitionManager.beginDelayedTransition(constraintLayout)
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                set.applyTo(constraintLayout)
            }
        }


    }
}
