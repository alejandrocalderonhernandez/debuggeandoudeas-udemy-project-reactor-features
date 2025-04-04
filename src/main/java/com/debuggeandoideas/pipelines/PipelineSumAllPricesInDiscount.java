package com.debuggeandoideas.pipelines;

import com.debuggeandoideas.database.Database;
import com.debuggeandoideas.models.Videogame;
import reactor.core.publisher.Mono;

import javax.naming.OperationNotSupportedException;

public class PipelineSumAllPricesInDiscount {

    /*
          Sum all prices of each videogame in discount
     */
    public static Mono<Double> getSumAllPricesInDiscount() {
        return Database.getDataAsFlux()
                .filter(Videogame::getIsDiscount)
                .map(Videogame::getPrice)
                .reduce(0.0, Double::sum);
    }
}
