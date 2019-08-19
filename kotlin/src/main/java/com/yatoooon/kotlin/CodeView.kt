package com.yatoooon.kotlin

import android.content.Context
import android.graphics.Paint
import android.widget.TextView

//class CodeView : TextView {
//    constructor(context: Context) : super(context)  //次级构造
//}

class CodeView constructor(context: Context) : TextView(context) { //主构造器 constructor可以省略
    val color = context.getString(R.string.app_name)  //成员变量初始化可以直接访问到主构造参数
    val paint = Paint()
    init {    //初始化模块按照在文件中的顺序执行
        paint.color = resources.getColor(R.color.colorPrimary)
    }
//    val paint = Paint()
}