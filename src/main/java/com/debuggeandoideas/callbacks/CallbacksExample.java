package com.debuggeandoideas.callbacks;

import com.debuggeandoideas.database.Database;
import com.debuggeandoideas.models.Videogame;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Slf4j
public class CallbacksExample {

    public static Flux<Videogame> callbacks() {

        return Database.getDataAsFlux()
                .doOnSubscribe(subs -> log.info("[doOnSubscribe]"))
                .doOnRequest(n -> log.info("[doOnRequest]: {}", n))
                .doOnNext(videogame -> log.info("[doOnNext]: {}", videogame.getName()))
                .doOnCancel(() -> log.warn("[doOnCancel]"))
                .doOnError(err -> log.error("[doOnError]", err))
                .doOnComplete(() -> log.info("[doOnComplete] success"))
                .doOnTerminate(() -> log.info("[doOnTerminate]"))
                .doFinally(signal -> log.warn("[doFinally]: {}", signal));
    }
}
