package org.codejudge.sb.entity;//package com.example.bookmyshow.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Table(name = "seat")
//@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//public class Seat implements Serializable {
//
//    private SeatKey seatKey;
//    private Theatre theatre;
//    private Boolean reserved;
//
//    public Seat() {
//    }
//
//    public Seat(Theatre theatre, Character row, Integer column, Boolean reserved) {
//        this.seatKey = new SeatKey(row, column, theatre.getTheatreId());
//        this.theatre = theatre;
//        this.reserved = reserved;
//    }
//
//    @EmbeddedId
//    public SeatKey getSeatKey() {
//        return seatKey;
//    }
//
//    public void setSeatKey(SeatKey seatKey) {
//        this.seatKey = seatKey;
//    }
//
//    @ManyToOne
//    @MapsId("theatre_id")
//    @JoinColumn(name = "theatre_id")
//    @JsonIgnore
//    public Theatre getTheatre() {
//        return theatre;
//    }
//
//    public void setTheatre(Theatre theatre) {
//        this.theatre = theatre;
//    }
//
//    public Boolean getReserved() {
//        return reserved;
//    }
//
//    public void setReserved(Boolean reserved) {
//        this.reserved = reserved;
//    }
//}
