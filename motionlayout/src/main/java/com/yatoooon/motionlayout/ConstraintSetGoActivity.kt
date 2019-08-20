package com.yatoooon.motionlayout

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.transition.Scene
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ConstraintSetGoActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraintset_go)

        bind()
    }

    private fun bind() {
        findViewById<ImageView>(R.id.head).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_desc).text =
            "测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了测试ConstraintSet了"
    }

    var tag = false
    override fun onClick(v: View) {
        val root = findViewById<ConstraintLayout>(R.id.root)
        val start = Scene.getSceneForLayout(root, R.layout.activity_constraintset_go_start, this)
        val end = Scene.getSceneForLayout(root, R.layout.activity_constraintset_go_end, this)
        if (tag) {
            TransitionManager.go(start)
        } else {
            TransitionManager.go(end)
        }
        bind() //需要重新绑定
        tag = !tag
    }
}
