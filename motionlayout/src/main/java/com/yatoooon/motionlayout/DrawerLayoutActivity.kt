package com.yatoooon.motionlayout

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.View

class DrawerLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawerlayout)
        val motionLayout = findViewById<MotionLayout>(R.id.motion_layout)

        findViewById<DrawerLayout>(R.id.drawer_layout).addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(p0: Int) {
            }

            override fun onDrawerClosed(p0: View) {
            }

            override fun onDrawerOpened(p0: View) {
            }

            override fun onDrawerSlide(p0: View, p1: Float) {
                motionLayout.progress = p1
            }
        })
    }
}
