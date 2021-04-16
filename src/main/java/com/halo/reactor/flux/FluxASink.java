package com.halo.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author shoufeng
 */

public class FluxASink {
	public static void main(String[] args) {
		AtomicInteger state = new AtomicInteger(0);
		Flux.generate(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return state.incrementAndGet();
			}
		}, new BiFunction<Integer, SynchronousSink<Integer>, Integer>() {
			@Override
			public Integer apply(Integer integer, SynchronousSink<Integer> synchronousSink) {
				System.out.println("state: " + integer);
				if (integer == 50) {
					synchronousSink.complete();
				} else {
					synchronousSink.next(integer * integer);
				}
				return integer + 1;
			}
		}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println("接受到：" + integer);
			}
		});
		Flux.create(new Consumer<FluxSink<Integer>>() {
			@Override
			public void accept(FluxSink<Integer> fluxSink) {

			}
		});
	}
}
