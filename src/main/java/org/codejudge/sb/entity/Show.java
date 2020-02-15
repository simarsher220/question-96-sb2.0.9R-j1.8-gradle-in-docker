package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "show")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Show {

    private ShowKey id;
    private Movie movie;
    private Theatre theatre;

    public Show() {
    }

    public Show(Theatre b, Movie p, Date date, Time time) {
        // create primary key
        this.id = new ShowKey(b.getTheatreId(), p.getMovieId(), date, time);

        // initialize attributes
        this.theatre = b;
        this.movie = p;
        // update relationships to assure referential integrity
//        p.getQuantities().add(this);
//        b.getQuantities().add(this);
    }

    @EmbeddedId
    public ShowKey getId() {
        return id;
    }

    public void setId(ShowKey id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @ManyToOne
    @MapsId("theatre_id")
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
