package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    private Integer movieId;
    private String movieName;
    private String movieTrailer;
    private String movieOverview;
    private String moviePoster;
    private Integer length;
    private Set<Show> shows;

    public Movie(Integer movieId, String movieName, String movieTrailer, String movieOverview, String moviePoster, Set<Show> shows, Integer length) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieTrailer = movieTrailer;
        this.movieOverview = movieOverview;
        this.moviePoster = moviePoster;
        this.shows = shows;
        this.length = length;
    }

    public Movie() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @NotNull
    @Column(name = "movie_name", unique = true)
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @NotNull
    @Column(name = "movie_trailer")
    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    @NotNull
    @Column(name = "movie_overview", columnDefinition="LONGTEXT")
    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    @NotNull
    @Column(name = "movie_poster")
    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

    @NotNull
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
