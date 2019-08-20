package com.yatoooon.motionlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.motionlayout.util.dp

class ObjectAnimatorActivity : AppCompatActivity() {
    lateinit var rootview: LinearLayout
    var i = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)


        rootview = findViewById<LinearLayout>(R.id.root)
        findViewById<ImageView>(R.id.view).setOnClickListener {

            i = if (i == 100) 200 else 100
            TransitionManager.beginDelayedTransition(rootview)


            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = i.toFloat().dp().toInt()
            layoutParams.height = i.toFloat().dp().toInt()
            layoutParams.gravity = if (i == 100) Gravity.TOP else Gravity.CENTER
            it.layoutParams = layoutParams

        }
    }
}
