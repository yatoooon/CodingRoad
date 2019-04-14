package com.yatoooon.hotupdate;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        File apk = new File(getCacheDir() + "/hotupdate-debug.apk");
        File apk = new File(getCacheDir() + "/hotupdate-title.dex");
        if (apk.exists()) {

            try {
                ClassLoader classLoader = getClassLoader();
                Class loaderClass = BaseDexClassLoader.class;
                Field pathListField = loaderClass.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                Object pathListObject = pathListField.get(classLoader);
                Class pathListClass = pathListObject.getClass();
                Field dexElementsField = pathListClass.getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);


                if (apk.getPath().endsWith("apk")) {
                    Object newClassLoader;
                    if (Build.VERSION.SDK_INT < 20) {
                        newClassLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, getClassLoader().getParent());
                    } else {
                        newClassLoader = new PathClassLoader(apk.getPath(), null);  //4.4 会报错  应该采用dexclassloader加载
                    }

                    Object newPathListObject = pathListField.get(newClassLoader);
                    Object newDexElementsObject = dexElementsField.get(newPathListObject);
                    dexElementsField.set(pathListObject, newDexElementsObject);

                } else if (apk.getPath().endsWith("dex")) {

                    Object dexElementsObject = dexElementsField.get(pathListObject);
                    Object newClassLoader;
                    if (Build.VERSION.SDK_INT < 20) {
                        //直接使用dex 不包裹为 jar或者 zip 或者 apk  在4.4会报错 暂未找到解决办法
                        newClassLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, getClassLoader().getParent());
                    } else {
                        newClassLoader = new PathClassLoader(apk.getPath(), null);
                    }
                    Object newPathListObject = pathListField.get(newClassLoader);
                    Object newDexElementsObject = dexElementsField.get(newPathListObject);
                    int oldLength = Array.getLength(dexElementsObject);
                    int newLength = Array.getLength(newDexElementsObject);
                    Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
                    for (int i = 0; i < newLength; i++) {
                        Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
                    }
                    for (int i = 0; i < oldLength; i++) {
                        Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
                    }
                    dexElementsField.set(pathListObject, concatDexElementsObject);

                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
