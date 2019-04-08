package com.yatoooon.recyclerview_core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

     /* listview的局限和recyclerview的优势:
        1.listview只有纵向列表一种布局      recyclerview 默认支持Linear, Grid, Staggered Grid 三种布局
        2.listview没有支持动画的api         recyclerview 有友好的 ItemAnimator 动画 API
        3.listview没有强制实现viewholder   recyclerview 强制实现viewholder
        4.listview性能不如recyclerview     recyclerview 有更好的性能
         * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);   //recyclerview是什么?: A flexible view for providing a limited window into a large data set

        //recyclerview的重要组件   1.layoutmanager 2.item animator 3 adapter



        UserRVAdapter rvAdapter = new UserRVAdapter(this);
        recyclerView.setAdapter(rvAdapter);
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三", "18"));
        userList.add(new User("李四", "19"));
        userList.add(new User("王五", "20"));
        rvAdapter.setData(userList);
        /*
        recyclerview的缓存机制
         Recycler
        1 CreateView
        2 (1) ScrapView:  屏幕内部的view     直接复用用position找
          (2) Cache:      移出屏幕的view放进cache    直接复用用position找
          (3) ViewCacheExtension    用户自定义缓存   可用性非常有限
          (4) RecycledViewPool    被废弃的view  脏数据  用viewtype找  重新绑定数据
         */


        /*性能优化
        1.在createViewholder里面设置点击监听器  而不是bindviewholder里面
        2.linearLayoutManager.setInitialPrefetchItemCount(n);//嵌套的橫向列表初次显示时可以见的item的个数;
        3.Recyclerview.setHasFixedSize();
        4.多个recyclerview共用RecycledViewPool
        5.DiffUtil   比较两个List  适用于刷新整个页面  但是有部分数据可能相同的情况   (1)局部更新方法 notifyItemXXX() 不适用于所有情况 (2) notifyDataSetChange() 会导致整个布局重绘，
        6.异步diff  ● 使用 Thread/Handler 将 DiffResult 发送到主线程 ● 使用 RxJava 将 calculateDiff 操作放到后台线程 ● 使用 Google 提供的 AsyncListDiffer (Executor) / ListAdapter
        * */


        //ItemDecoration 可以做什么    ● Drawing dividers between items ● Highlights ● Visual grouping boundaries
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


    }


}
