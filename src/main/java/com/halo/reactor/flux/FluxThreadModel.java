package com.halo.reactor.flux;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author shoufeng
 */

public class FluxThreadModel {

	public static void main(String[] args) throws InterruptedException {

		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(Thread.currentThread().hashCode());

		Flux.range(1, 5).handle((integer, synchronousSink) -> {
			System.out.println("当前线程: " + Thread.currentThread().getName());
			System.out.println("是否守护线程: " + Thread.currentThread().isDaemon());
			if (integer % 2 == 0) {
				synchronousSink.next(integer);
			}
		}).subscribeOn(Schedulers.newSingle("subscribe_a_single_scheduler")).publishOn(Schedulers.newSingle("publish_a_single_scheduler")).subscribe(new BaseSubscriber<Object>() {
			@Override
			protected void hookOnNext(Object value) {
				System.out.println("当前线程: " + Thread.currentThread().getName());
				System.out.println("是否守护线程: " + Thread.currentThread().isDaemon());
				System.out.println(Thread.currentThread().hashCode());
				System.out.println("得到值: " + value);
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("全部消费完");
				System.out.println("当前线程: " + Thread.currentThread().getName());
			}
		});

		Flux.range(5, 1000000).publishOn(Schedulers.elastic()).subscribe(new BaseSubscriber<Integer>() {
			@Override
			protected void hookOnNext(Integer value) {
				System.out.println("当前线程: " + Thread.currentThread().getName());
				System.out.println("是否守护线程: " + Thread.currentThread().isDaemon());
				try {
					Thread.sleep(10 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("得到值: " + value);
			}
		});

	}

}
