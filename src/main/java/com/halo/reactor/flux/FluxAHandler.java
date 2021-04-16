package com.halo.reactor.flux;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author shoufeng
 */

public class FluxAHandler {
	public static void main(String[] args) {
		Flux.just(1,2,3,4,5).handle((integer, synchronousSink) -> {
			if (integer > 3){
				return;
			}
			synchronousSink.next(integer);
		}).subscribe(new BaseSubscriber<Object>() {
			@Override
			protected void hookOnNext(Object value) {
				System.out.println(value);
				super.hookOnNext(value);
			}
		});
	}
}
