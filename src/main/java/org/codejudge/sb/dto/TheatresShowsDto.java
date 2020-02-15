package org.codejudge.sb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheatresShowsDto {

    private MovieDto movie;
    private List<TheatreShowsDto> theatreShowsDtos;

    @JsonProperty("movie")
    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    @JsonProperty("theatres")
    public List<TheatreShowsDto> getTheatreShowsDtos() {
        return theatreShowsDtos;
    }

    public void setTheatreShowsDtos(List<TheatreShowsDto> theatreShowsDtos) {
        this.theatreShowsDtos = theatreShowsDtos;
    }
}
