package com.yatoooon.motionlayout

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ConstraintSetActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraintset_start)

        findViewById<ImageView>(R.id.head).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_desc).text =
            "测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了"
    }

    var tag = false
    override fun onClick(v: View?) {

        val root = findViewById<ConstraintLayout>(R.id.root)
        TransitionManager.beginDelayedTransition(root)
        val constraintSet = ConstraintSet()

        if (tag) {
            constraintSet.clone(this, R.layout.activity_constraintset_start)
        } else {
            constraintSet.clone(this, R.layout.activity_constraintset_end)
        }
        constraintSet.applyTo(root)
        tag = !tag
    }
}
