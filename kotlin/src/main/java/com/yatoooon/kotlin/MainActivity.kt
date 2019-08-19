package com.yatoooon.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Retrofit
import kotlin.properties.Delegates


fun print() {
    println("Hello World!")
    println(doubleX(2))
}

fun doubleX(x: Int): Int {
    return x * 2
}

var i = 18
val name = "yatoooon"


class TestKotlin(x: Int) : View.OnClickListener {
    val kotlin = TestKotlin(2)
    var mX: Int = 0

    init {
        mX = x
    }

    //    constructor(x: Int) {
//        mX = x
//    }
    override fun onClick(v: View?) {

    }
}

class User(val map: Map<String, Any?>) {
    //属性委托map
    val name: String by map
    val age: Int     by map
}

class MainActivity : AppCompatActivity() {

    //        var tvHelloWorld: TextView? = null  //可空类型
    lateinit var tvHelloWorld: TextView
    lateinit var etHellowWorld: EditText


    var test: String? = null


    var p: String by Delegate() //属性委托
    val lazyValue: String by lazy {
        //属性委托lazy
        println("computed!")
        "Hello"
    }

    class User {
        var name: String by Delegates.observable("<no name>") { prop, old, new ->
            println("$old -> $new")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etHellowWorld = findViewById<EditText>(R.id.et_helloworld)
        tvHelloWorld = findViewById<TextView>(R.id.tv_helloworld)
//        tvHelloWorld?.text = "hduwiahduiwag"  //可空类型
//        tvHelloWorld!!.text = "hduwiahduiwag"  //非空断言
        tvHelloWorld.text = "hduwiahduiwag"

        val kClass = MainActivity::class
        val java = MainActivity::class.java


        //editext 和 textview 不一样
        val newEditable = Editable.Factory.getInstance().newEditable("Kotlin")
        etHellowWorld.text = newEditable


        val intArrayOf = intArrayOf(1, 2, 3)
        val stringArrayOf = arrayOf("1", "2", "3")

        //java的基本数据类型在kotlin都有对应,装箱类型对应kotlin里面的可空类型

        sMethod(sName)
        StaticFileAndMethod1.name
        staticFileAndMethod2.name
        tvHelloWorld.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })

        val money = 100
        val multiple = 2
        var text = "现在有${money}元"
        var text2 = "现在有 ${multiple}个${money}元"

        val a = """
            dwadwadw
            dwadwadwa
            dwadwadawd
            """.trimIndent()

        println(a)


        if (money in 0..100) {
            println(true)
        }


        when (money) {
            0 -> print(money)
            100 -> print(money)
            200 -> print(money)
        }


        this@MainActivity.tvHelloWorld.text = "dwadwadwadw"

        for (i in intArrayOf) {
            println(i)
        }


        val user1 = UserBean(2, "yatoooon")
        val user2 = UserBean(2, "yatoooon")
        println("equals," + user1.equals(user2)) //结构相等  等于   ==
        println("==," + (user1 == (user2)))  //结构相等
        println("===," + (user1 === (user2)))  //引用相等
        println(user1.toString())
        val user3 = user1.copy()
        println(user3 == user1)
        println(user3 === user1)
        println(user3)
        val (age, name) = user1  //解构
        println(age == user1.component1())
        println(name == user1.component2())


        val x = test ?: "Elvis 操作符"
        val y = test ?: return
        val z = test ?: throw IllegalArgumentException("content expected")


        val count = 1.plus(2).minus(3).times(4).div(5)

        val colorRes1 = when (count) {
            1 -> R.color.colorAccent
            2 -> R.color.colorPrimary
            3 -> R.color.colorPrimaryDark
            else -> R.color.colorPrimary
        }

        val colorRes2 = when {
            count > 0 -> R.color.colorAccent
            count <= 0 -> R.color.colorPrimaryDark
            else -> R.color.colorAccent
        }


        tvHelloWorld.setOnClickListener {
            it.id            //隐式it访问只有一个参数的lambda
        }

        repeat(100) {
            print(it)
        }
        for (i in 0..99) {
            print(i)
        }
        for (i in 0 until 100) {
            print(i)
        }


        toast("成员函数或者扩展函数")  //Util文件中扩展函数  但是成员函数优先被调用

//        val sum: (Int, Int) -> Int = fun(a: Int, b: Int) = a + b
//        val sum = fun(a: Int, b: Int) = a + b
        val sum = { a: Int, b: Int -> a + b }


        //属性委托
        println(p)
        println(lazyValue)
        println(lazyValue)
        val user = User()
        user.name = "first"
        user.name = "second"

        val usermap = User(
            mapOf(
                "name" to "John Doe",
                "age" to 25
            )
        )
        println(usermap.name)
        println(usermap.age)


        //kotlin标准函数
        //1.返回自身 作用域中使用this作为参数用apply      使用it作为参数使用also
        //2.不需要返回自身 作用域中使用this作为参数使用run   使用it作为参数使用let
        val apply = tvHelloWorld.apply { this.text = "dwahdwahjawlhdwa" }
        val also = tvHelloWorld.also { it.text = "dwahjdgwahkdgwahdgwhak" }
        tvHelloWorld.run { this.text = "dwjakldwajkldjwakldjwa" }
        tvHelloWorld.let { it.text = "dwjakldwajkldjwakldjwa" }

    }

    inner class InnerClass { //嵌套内部类
        internal var inner: String? = null  //internal 对当前模块可见
    }


    fun func() {
        fun innerFunc() {} //嵌套函数
    }

    fun get(str: String) = str + "哈哈哈哈"  //=号代替return

    fun toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {  //函数参数设置默认值代替java的函数重载
        Toast.makeText(this, text, duration).show()
    }

    @JvmOverloads
    fun f(a: String, b: Int = 0, c: String = "abc") {  //JvmOverloads注解对java暴漏重载参数
    }


    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
        //内联函数可以减少函数类型生成的对象
    }


    //inline + reified   真泛型效果
    val RETROFIT = Retrofit.Builder()
        .baseUrl("https://api.hencoder.com/")
        .build()

    inline fun <reified T> create(): T {
        return RETROFIT.create(T::class.java)
    }

    val api = create<API>()




//    class StringPrinter : StringProcessor { //平台类型处理为可空类型  默认
//        override fun process(s: String?) {
//            if (s != null) {  //必须自己判断是否为空
//                println(s.length)
//            }
//        }
//    }
//
//    class StringPrinter : StringProcessor { //平台类型处理为非可空类型
//        override fun process(s: String) {
//            println(s.length)
//        }
//    }
}
