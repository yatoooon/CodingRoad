package com.yatoooon.kotlin

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) { //扩展函数   是编译期确定的静态函数  不具备多态性
    Toast.makeText(this, message, duration).show()
}