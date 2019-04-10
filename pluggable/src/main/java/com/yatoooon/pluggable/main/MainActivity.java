package com.yatoooon.pluggable.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yatoooon.pluggable.R;
import dalvik.system.DexClassLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //正常
//        new Util().say();

        //正常
//        try {
//            Util.class.newInstance().say();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }

        //反射
//        Class<Util> utilClass = Util.class;
//        Util util = null;
//        try {
//            util = utilClass.newInstance();
//            Method say = utilClass.getDeclaredMethod("say");
//            say.invoke(util);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }


//        //去掉public的用反射 会出现IllegalAccessException  不知道为什么在android8.0没有出现
//        try {
//            Class utilClass = Class.forName("com.yatoooon.pluggable.hide.Util");
//            Constructor constructor = utilClass.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            Object util = constructor.newInstance();
//            Method say = utilClass.getDeclaredMethod("say");
//            say.setAccessible(true);
//            say.invoke(util);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        File apk = new File(getCacheDir() + "/pluggable_plugin-debug.apk");
        if (!apk.exists()) {
            try {
                InputStream is = getAssets().open("pluggable_plugin-debug.apk");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                FileOutputStream fos = new FileOutputStream(apk);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, getClassLoader().getParent());
        try {
            Class pluginUtilsClass = classLoader.loadClass("com.yatoooon.pluggable_plugin.Util");
            Constructor constructor = pluginUtilsClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object pluginUtils = constructor.newInstance();
            Method pluginMethod = pluginUtilsClass.getDeclaredMethod("plugin");
            pluginMethod.setAccessible(true);
            pluginMethod.invoke(pluginUtils);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


}
