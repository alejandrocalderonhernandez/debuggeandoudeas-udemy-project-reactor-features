package com.debuggeandoideas.pipelines;


import com.debuggeandoideas.database.Database;
import com.debuggeandoideas.models.Review;
import reactor.core.publisher.Flux;

public class PipelineAllComments {

    public static Flux<String> getAllReviewsComments() {
        return Database.getDataAsFlux()
                .flatMap(videogame -> Flux.fromIterable(videogame.getReviews())) //Videogame -> Review
                .map(Review::getComment);
    }
}
