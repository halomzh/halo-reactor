package com.halo.reactor.flux;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author shoufeng
 */

public class FluxAError {
	public static void main(String[] args) {
		Flux.range(1, 10).map(integer -> {
			if (integer > 5) {
				throw new RuntimeException("大于5了");
			}
			return integer;
		}).subscribe(new BaseSubscriber<Integer>() {
			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("接收到值: " + value);
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("完成");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				System.out.println("捕获到异常: " + throwable);
			}
		});

		//吃掉异常return一个值
		Flux.range(5, 5).map(integer -> {
			if (integer == 8) {
				throw new RuntimeException("值等于8了");
			}
			return integer;
		}).onErrorReturn(100).subscribe(new BaseSubscriber<Object>() {
			@Override
			protected void hookOnNext(Object value) {
				System.out.println("获取到值");
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("完成");
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				System.out.println("捕获到异常: " + throwable);
			}
		});
	}

}
