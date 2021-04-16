package com.halo.reactor.mono;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * @author shoufeng
 */

public class MonoFactory {
	public static void main(String[] args) {
		Mono<Object> empty = Mono.empty();
		Mono<Integer> just = Mono.just(0);
		Mono<String> stringMono = Mono.fromSupplier(new Supplier<String>() {
			@Override
			public String get() {
				return "提供一个值";
			}
		});
	}
}
