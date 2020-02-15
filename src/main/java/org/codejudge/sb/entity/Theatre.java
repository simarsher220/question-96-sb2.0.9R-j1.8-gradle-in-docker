package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "theatre")
public class Theatre implements Serializable {

    private Integer theatreId;
    private String theatreName;
    private String theatreLocation;
    private String city;
    private Integer pincode;
    private Set<Show> shows;
//    private Set<Seat> seats;

    public Theatre() {
    }

    public Theatre(Integer theatreId, String theatreName, String theatreLocation, String city, Integer pincode, Set<Show> shows) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
        this.city = city;
        this.pincode = pincode;
        this.shows = shows;
//        this.seats = seats;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatre_id")
    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    @NotNull
    @Column(name = "theatre_name")
    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    @NotNull
    @Column(name = "theatre_location")
    public String getTheatreLocation() {
        return theatreLocation;
    }

    public void setTheatreLocation(String theatreLocation) {
        this.theatreLocation = theatreLocation;
    }

    @NotNull
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    @Column(name = "pincode")
    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @OneToMany(mappedBy = "theatre")
    @JsonIgnore
    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

//    @OneToMany(mappedBy = "theatre")
//    @JsonIgnore
//    public Set<Seat> getSeats() {
//        return seats;
//    }
//
//    public void setSeats(Set<Seat> seats) {
//        this.seats = seats;
//    }
}
