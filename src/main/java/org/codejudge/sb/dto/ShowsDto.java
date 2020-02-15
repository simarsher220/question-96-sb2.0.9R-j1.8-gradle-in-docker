package org.codejudge.sb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowsDto {

    private MovieDto movie;
    private TheatreDto theatre;
    private List<ShowDto> showDtos;

    @JsonProperty("movie")
    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    @JsonProperty("theatre")
    public TheatreDto getTheatre() {
        return theatre;
    }

    public void setTheatre(TheatreDto theatre) {
        this.theatre = theatre;
    }

    @JsonProperty("shows")
    public List<ShowDto> getShowDtos() {
        return showDtos;
    }

    public void setShowDtos(List<ShowDto> showDtos) {
        this.showDtos = showDtos;
    }
}
