package com.yatoooon.annotation_lib;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Binding {

//    /**
//     * 纯反射的方式 实现butterknife
//     */
//    public static void bind(Activity activity) {
//        for (int i = 0; i < activity.getClass().getDeclaredFields().length; i++) {
//            Field field = activity.getClass().getDeclaredFields()[i];
//            BindView bindView = field.getAnnotation(BindView.class);
//            if (bindView != null) {
//                field.setAccessible(true);
//                try {
//                    field.set(activity, activity.findViewById(bindView.value()));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    /**
     * 利用 annotation processor 实现butterknife
     */
    public static void bind(Activity activity) {
        try {
            Class bindingClass = Class.forName(activity.getClass().getCanonicalName() + "Binding");
            Class activityClass = Class.forName(activity.getClass().getCanonicalName());
            Constructor constructor = bindingClass.getDeclaredConstructor(activityClass);
            constructor.newInstance(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
