package org.codejudge.sb.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Embeddable
public class ShowKey implements Serializable {

    private Integer theatreId;
    private Integer movieId;
    private Date date;
    private Time time;

    public ShowKey() {
    }

    public ShowKey(Integer theatreId, Integer movieId, Date date, Time time) {
        this.theatreId = theatreId;
        this.movieId = movieId;
        this.date = date;
        this.time = time;
    }

    @NotNull
    @Column(name = "theatre_id")
    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    @NotNull
    @Column(name = "movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @NotNull
    @Column(name = "show_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "show_time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ShowKey that = (ShowKey) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(theatreId, that.theatreId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, theatreId, date, time);
    }
}
