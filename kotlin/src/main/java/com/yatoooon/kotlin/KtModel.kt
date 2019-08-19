package com.yatoooon.kotlin

class KtModel(age: Int, name: String) : KtParentModel(age, name) {
    @JvmField
    var age: Int? = null
    var name: String? = null

    init {
        this.age = age
        this.name = name
    }

    fun print(): Unit {
        println("KtModel(age=$age, name=$name)")
    }


}