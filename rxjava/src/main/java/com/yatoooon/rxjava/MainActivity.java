package com.yatoooon.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Observable<Integer> observable;
    private Observable<Integer> observable2;
    private Function<Integer, String> function;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //被观察者
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        //被观察者2
        observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());


        //中间节点
        function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        };


        //观察者
        //Disposable 用于断开上下游的链接  导致观察者收不到事件 并不影响上游发送事件    用法:例如在退出activity的时候切断链条 不会再更新UI
        observer = new Observer<String>() {
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


        /* 整体看做是一条链
         * 链的 上游是Observable  下游是Observer   中间节点既是上游也是下游
         * 中间节点:  用一些操作符例如map   基于原Observable创建一个新的Observable  新的Observable内部创建一个Observer    通过新的Observable中的subscribeActual()和新的Observer的onXxx()来实现自己的中间角色
         * */


//        最基本的一些规则
//        1 上游可以发送无限个onNext   下游可以接受无限个onNext
//        2 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
//        3 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
//        4 上游可以不发送onComplete或onError.
//        5 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然


//        setExample1();   //最简单的
//        setExample2();   //单一接受的方法
//        setExample3();   //flatmap
//        setExample4();   //concatMap
//        setExample5();   //zip
//        setExample6();   //引入Backpressure
        setExample7();   //Flowable


    }

    private void setExample7() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                //上游获取下游的处理能力
                // 当上下游在同一个线程中的时候，在下游调用request(n)就会直接改变上游中的requested的值，多次调用便会叠加这个值，而上游每发送一个事件之后便会去减少这个值，当这个值减少至0的时候，继续发送事件便会抛异常
                // 当上下游工作在不同的线程里时，每一个线程里都有一个requested，而我们调用request（1000）时，实际上改变的是下游主线程中的requested，而上游中的requested的值是由RxJava内部调用request(96)去设置的，这个调用会在合适的时候自动触发。
                //在某一些场景下，可以在发送事件前先判断当前的requested的值是否大于0，若等于0则说明下游处理不过来了，则需要等待.        响应式拉取事件(Reactive Extensions)
                Timber.d("current requested: %s", emitter.requested());


                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
            //选这个参数会在上下游不均衡的时候抛出MissingBackpressureException异常
            //BackpressureStrategy.BUFFER 更大的缓冲区  onBackpressureBuffer()
            //ackpressureStrategy.DROP 把存不下的事件丢弃 onBackpressureDrop()
            //BackpressureStrategy.LATEST 只保留最新的事件 onBackpressureLatest()
        }, BackpressureStrategy.ERROR)
                .onBackpressureBuffer()//对于不是自己创建的FLowable  可以调用对应的方法来实现相应的策略
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //下游的处理能力默认128
                        //没有这句话  在同步会抛出MissingBackpressureException   在异步不会抛异常但是上游发送的所有事件下游一个也没有收到  因为下游没有表明处理能力
                        //每调用一次就请求一下
                        s.request(Long.MAX_VALUE);

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Timber.d("onNext" + String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.d(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @SuppressLint("CheckResult")
    private void setExample6() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                    Thread.sleep(2000);  //发送事件之后延时2秒    1 从速度上进行治理, 减缓事件发送进水缸的速度
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io()).sample(2, TimeUnit.SECONDS);  //2是从数量上进行治理, 减少发送进水缸里的事件


        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + "      " + integer2;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Timber.d(s);
            }
        });


        //1 上下游在同一个线程时  同步订阅关系   上游发一个 等下游收一个处理完了  上游才接着发
        //2  上下游不在同一个线程时   异步订阅关系    上游不等下游处理   直接发        引出  背压Flowable等概念

        //手工解决的办法有两个  如上


    }

    private void setExample5() {
        //操作符zip  通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。   两个observable在一个线程的话会按先后顺序发送
        Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return String.valueOf(integer + "zip操作符" + integer);
            }
        }).subscribe(observer);
    }

    private void setExample4() {
        //操作符concatMap   flatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable。严格按照上游发送的顺序来发送
        observable.concatMap(new Function<Integer, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(String.valueOf(integer));
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(observer);
    }

    private void setExample3() {
        //操作符flatmap   flatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable。  无序的
        //简单的来说  就是将一个Observable转换为另一个Observable
        observable.flatMap(new Function<Integer, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(String.valueOf(integer));
                }
                return Observable.fromIterable(list).delay(50, TimeUnit.MILLISECONDS);
            }
        }).subscribe(observer);
    }

    @SuppressLint("CheckResult")
    private void setExample2() {
        //subscribe有多个重载的方法   如果想简单的写个接受的方法   用Consumer
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }

    private void setExample1() {

        //观察者订阅被观察者    看起来有些别扭    但是确实是后面的observer 观察 observable
        observable
                .subscribeOn(Schedulers.newThread())      //控制上游线程    写到哪都无所谓,多次指定只有第一次有效
                .observeOn(AndroidSchedulers.mainThread())   //切换下游线程   每次指定都会切换一次下游线程
                .map(function)        //最简单的操作符map    将上游发送的每个Integer转换为了下游需要的String
                .subscribe(observer);

    }
}
