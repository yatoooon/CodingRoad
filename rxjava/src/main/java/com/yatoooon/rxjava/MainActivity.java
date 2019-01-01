package com.yatoooon.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import timber.log.Timber;

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
            public void onSubscribe(Disposable d) {
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
        //用了一个map操作符    将上游发送的Integer转换为了下游需要的String
        observable.map(function).subscribe(observer);



        /* 整体看做是一条链
         * 链的 上游是Observable  下游是Observer   中间节点既是上游也是下游
         * 中间节点:  用一些操作符例如map   基于原Observable创建一个新的Observable  新的Observable内部创建一个Observer    通过新的Observable中的subscribeActual()和新的Observer的onXxx()来实现自己的中间角色
         * */


    }
}
