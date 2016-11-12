package com.netcracker.cinema.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by gaya on 05.11.2016.
 */
@Getter
@Setter
public class Ticket {

    private long id;
    private long code;
    private String email;
    private int price;
    private boolean isPaid;
    private Seance seanceId;
    private Place placeId;
}
