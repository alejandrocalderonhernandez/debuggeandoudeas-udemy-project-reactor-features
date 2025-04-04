package com.debuggeandoideas.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Videogame {
    private String name;
    private Double price;
    private Console console;
    private List<Review> reviews;
    private String officialWebsite;
    private Boolean isDiscount;
    private Integer totalSold;

}
