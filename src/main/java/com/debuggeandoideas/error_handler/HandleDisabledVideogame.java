package com.debuggeandoideas.error_handler;

import com.debuggeandoideas.database.Database;
import com.debuggeandoideas.models.Console;
import com.debuggeandoideas.models.Videogame;
import reactor.core.publisher.Flux;

import java.util.List;

public class HandleDisabledVideogame {

    private static final Videogame DEFAULT_VIDEOGAME =
            Videogame.builder()
                    .name("Default")
                    .price(0.0)
                    .console(Console.ALL)
                    .reviews(List.of(

                    ))
                    .officialWebsite("https://www.default.com/")
                    .isDiscount(true)
                    .totalSold(0)
                    .build();

    public static Flux<Videogame> handleDisabledVideogames() {

        return Database.getDataAsFlux()
                .handle((vg, sink) -> {

                    if (Console.DISABLED == vg.getConsole()) {
                        sink.error(new RuntimeException("Videogame is disabled"));
                        return;
                    }

                    sink.next(vg);
                })
                .onErrorResume(error -> {
                    System.out.println("Error detected: " + error.getMessage());

                    return Flux.merge(
                            Database.getDataAsFlux(),
                            Database.fluxAssassinsDefault
                    );
                })
                .cast(Videogame.class)
                .distinct(Videogame::getName);
    }

    public static Flux<Videogame> handleDisabledVideogamesDefault() {

        return Database.getDataAsFlux()
                .handle((vg, sink) -> {

                    if (Console.DISABLED == vg.getConsole()) {
                        sink.error(new RuntimeException("Videogame is disabled"));
                        return;
                    }

                    sink.next(vg);
                })
                .onErrorReturn(DEFAULT_VIDEOGAME)
                .cast(Videogame.class);
    }


}
