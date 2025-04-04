package com.debuggeandoideas.pipelines;


import com.debuggeandoideas.database.Database;
import com.debuggeandoideas.models.Videogame;
import reactor.core.publisher.Flux;

public class PipelineTopSelling {


    /*
      return all names of videogames with have a sold > 80
     */
    public static Flux<String> getTopSellingVideogames() {

        return Database.getDataAsFlux()
                .filter(videogame -> videogame.getTotalSold() > 80)
                .map(Videogame::getName);
    }
}
