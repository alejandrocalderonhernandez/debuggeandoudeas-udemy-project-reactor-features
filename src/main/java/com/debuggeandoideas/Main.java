package com.debuggeandoideas;

import com.debuggeandoideas.models.Console;
import com.debuggeandoideas.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Main {


    private static boolean videogameForConsole(Videogame game, Console console) {
        return game.getConsole() == console || game.getConsole() == Console.ALL;
    }


    public static void main(String[] args) throws InterruptedException {

    Flux<Integer> coldPublisher = Flux.range(1, 10);

    log.info("Cold publisher");

    log.info("Subs 1 subscribed");
    coldPublisher.subscribe(n -> log.info("[s1] {}", n));

        log.info("Subs 2 subscribed");
        coldPublisher.subscribe(n -> log.info("[s2] {}", n));

        log.info("Subs 3 subscribed");
        coldPublisher.subscribe(n -> log.info("[s3] {}", n));



        Flux<Long> hotPublisher = Flux.interval(Duration.ofSeconds(1))
                        .publish()
                        .autoConnect();

        log.info("Hot publisher");

        log.info("Subs 4 subscribed");
        hotPublisher.subscribe(n -> log.info("[s4] {}", n));

        Thread.sleep(2000);
        log.info("Subs 5 subscribed");
        hotPublisher.subscribe(n -> log.info("[s5] {}", n));
        Thread.sleep(1000);
        log.info("Subs 6 subscribed");
        hotPublisher.subscribe(n -> log.info("[s6] {}", n));

        Thread.sleep(10000);











       /* Database.getDataAsFlux()

                .filterWhen(vg -> Mono.deferContextual(ctx -> {
                    var userId = ctx.getOrDefault("userId", "0");

                    if (userId.startsWith("1")) {
                        return Mono.just(videogameForConsole(vg, Console.XBOX));
                    } else if (userId.startsWith("2")) {
                        return Mono.just(videogameForConsole(vg, Console.PLAYSTATION));
                    }

                    return Mono.just(false);
                }))
                .contextWrite(Context.of("userId", "10020192"))
                .subscribe(vg -> log.info("Recommended name {} console {}", vg.getName(), vg.getConsole()));*/

      /*CallbacksExample.callbacks()
              .subscribe(
                      data -> log.debug(data.getName()), // onNext
                       err -> log.error(err.getMessage()), // onError
                                          () -> log.debug("Finish subs"));  //onFinally*/

        /*
        // Publisher
        Flux<String> flux =Flux.just("Java", "Spring", "Reactor", "R2DBC")
                .doOnNext(value -> log.info("[onNext]: " + value))
                .doOnComplete(() -> log.info("[onComplete]: Success"));

        // Consumer
        flux.subscribe(
                data -> log.info("Receiving: " + data),
                err -> log.info("Error: " + err.getMessage()),
                () -> log.info("Completed success")

        );

         */

       /* // call ms shipments
        Flux<String> fluxShipments = Flux.just("Shipment1", "Shipment2", "Shipment3").delayElements(Duration.ofMillis(120));
        // call ms warehouse
        Flux<String> fluxWarehouse = Flux.just("stock1", "stock2", "stock3").delayElements(Duration.ofMillis(50));
        // call ms payments
        Flux<String> fluxPayments = Flux.just("pay1", "pay2", "pay3").delayElements(Duration.ofMillis(150));
        // call ms confirm
        Flux<String> fluxConfirm = Flux.just("confirm1", "confirm2", "confirm3").delayElements(Duration.ofMillis(20));

        Flux<String> reportFlux = Flux.zip(fluxShipments, fluxWarehouse, fluxPayments, fluxConfirm)
                        .map(tuple ->
                                tuple.getT1() + " " +
                                tuple.getT2() + " " +
                                tuple.getT3() + " " +
                                tuple.getT4());


        reportFlux
                .doOnNext(System.out::println)
                .blockLast();*/
    }
}