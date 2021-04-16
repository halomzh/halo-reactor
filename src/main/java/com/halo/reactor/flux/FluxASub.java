package com.halo.reactor.flux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.function.Consumer;

/**
 * @author shoufeng
 */

public class FluxASub {
	public static void main(String[] args) {
		Flux.range(1, 5).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println("接收到: " + integer);
			}
		});
		Flux.range(6, 10).map(integer -> {
			if (integer < 7) {
				return integer;
			}
//			throw new RuntimeException("大于等于7了");
			return 0;
		}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println("获取到值: " + integer);
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) {
				System.out.println("接收到意外信息" + throwable);
			}
		}, new Runnable() {
			@Override
			public void run() {
				System.out.println("已完成");
			}
		});
		Flux.range(11, 15).subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				//request(n) 就是这样一个方法。它能够在任何 hook 中，通过 subscription 向上游传递 背压请求。这里我们在开始这个流的时候请求1个元素值。
//				request(1);
				request(0);
//				super.hookOnSubscribe(subscription);
			}

			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("next数据: " + value);
				super.hookOnNext(value);
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("onComplete");
				super.hookOnComplete();
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				System.out.println("onError: " + throwable);
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				super.hookOnCancel();
			}

			@Override
			protected void hookFinally(SignalType type) {
				System.out.println("接收到结束信号: " + type);
				super.hookFinally(type);
			}
		});
	}
}
