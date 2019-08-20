package com.yatoooon.motionlayout

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity

class CoordinatorLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_layout)
        val motionLayout = findViewById<MotionLayout>(R.id.motion_layout)
        findViewById<AppBarLayout>(R.id.appbarlayout).addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            motionLayout.progress = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
        })
    }
}
