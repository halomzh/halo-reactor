package com.halo.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @author shoufeng
 */

public class FluxSchedule {
	public static void main(String[] args) {
		Flux.interval(Duration.ofSeconds(1), Schedulers.newSingle("a_single_scheduler")).subscribe(new Consumer<Long>() {
			@Override
			public void accept(Long aLong) {
				System.out.println(new Date());
			}
		});
	}
}
