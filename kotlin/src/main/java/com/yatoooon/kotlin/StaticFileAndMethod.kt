package com.yatoooon.kotlin


const val sName = "staticName"
fun sMethod(str: String) {
    println(str)
}


object StaticFileAndMethod1 {
    @JvmStatic
    val sjvmName = "objectStaticName"
    var name: String? = "StaticFileAndMethod1"
}

class staticFileAndMethod2 {
    companion object {
        @JvmStatic
        val sjvmName = "companionStaticName"
        var name: String? = "staticFileAndMethod2"
    }
}