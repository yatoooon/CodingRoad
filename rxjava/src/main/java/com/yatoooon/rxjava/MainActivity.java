package com.yatoooon.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });


        //中间节点
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        };


        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {  //Disposable 用于断开上下游的链接  导致观察者收不到事件 并不影响上游发送事件    用法:例如在退出activity的时候切断链条 不会再更新UI
                Timber.d("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Timber.d("onNext%s", s);
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("onError");
            }

            @Override
            public void onComplete() {
                Timber.d("onComplete");
            }
        };

        //观察者订阅被观察者    看起来有些别扭    但是确实是后面的observer 观察 observable
        observable
                .subscribeOn(Schedulers.newThread())      //控制上游线程    写到哪都无所谓,多次指定只有第一次有效
                .observeOn(AndroidSchedulers.mainThread())   //切换下游线程   每次指定都会切换一次下游线程
                .map(function)        //最简单的操作符map    将上游发送的每个Integer转换为了下游需要的String
                .subscribe(observer);



        /* 整体看做是一条链
         * 链的 上游是Observable  下游是Observer   中间节点既是上游也是下游
         * 中间节点:  用一些操作符例如map   基于原Observable创建一个新的Observable  新的Observable内部创建一个Observer    通过新的Observable中的subscribeActual()和新的Observer的onXxx()来实现自己的中间角色
         * */


        //最基本的一些规则
        //1 上游可以发送无限个onNext   下游可以接受无限个onNext
        //2 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
        //3 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
        //4 上游可以不发送onComplete或onError.
        //5 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然


        //subscribe有多个重载的方法   如果想简单的写个接受的方法   用Consumer
        Disposable disposable = observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });


        //操作符flatmap   flatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable。
        observable.flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final ArrayList<String> list = new ArrayList<>();
                list.add(String.valueOf(integer));
                list.add(String.valueOf(integer));
                list.add(String.valueOf(integer));
                return Observable.fromIterable(list).delay(50, TimeUnit.MILLISECONDS);
            }
        }).subscribe(observer);


    }
}
