package com.halo.reactor.flux;

import com.google.common.collect.Lists;
import reactor.core.publisher.Flux;

/**
 * @author shoufeng
 */

public class FluxAFactory {
	public static void main(String[] args) {
		Flux<Integer> integerFlux = Flux.fromIterable(Lists.newArrayList(1, 2, 3, 4, 5, 6));
		Flux<Integer> integerFlux1 = Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});
		Flux<Integer> just = Flux.just(1, 2, 3, 4, 5, 6);
		Flux<Integer> range = Flux.range(1, 6);
	}
}
