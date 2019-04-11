package com.yatoooon.pluggable.proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import dalvik.system.DexClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyActivity extends Activity {


    private String apkPath;
    private Context mContext;
    private AssetManager mAssetManager;
    private Resources mResources;

    public static void start(Context context, String ProxyActivity, String ApkPath) {
        Intent starter = new Intent(context, ProxyActivity.class);
        starter.putExtra("ProxyActivity", ProxyActivity);
        starter.putExtra("ApkPath", ApkPath);
        context.startActivity(starter);
    }

    private Object pluginActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getStringExtra("ProxyActivity");
        apkPath = getIntent().getStringExtra("ApkPath");


        loadRes(apkPath);
        DexClassLoader classLoader = new DexClassLoader(apkPath, getCacheDir().getPath(), null, getClass().getClassLoader());
        try {
            Class pluginUtilsClass = classLoader.loadClass(className);
            Constructor constructor = pluginUtilsClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            pluginActivity = constructor.newInstance();


            Method setProxyActivity = pluginUtilsClass.getMethod("setProxyActivity", Activity.class);
            setProxyActivity.setAccessible(true);
            setProxyActivity.invoke(pluginActivity, this);


            Method pluginMethod = pluginUtilsClass.getDeclaredMethod("onCreate");
            pluginMethod.setAccessible(true);
            pluginMethod.invoke(pluginActivity);

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


    private void loadRes(String dexPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            mAssetManager = assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = super.getResources();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
    }


    @Override
    public AssetManager getAssets() {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }

    @Override
    public Resources getResources() {
        return mResources == null ? super.getResources() : mResources;
    }


}
