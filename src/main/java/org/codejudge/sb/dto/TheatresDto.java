package org.codejudge.sb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheatresDto {

    private List<TheatreDto> theatreDtos;

    @JsonProperty("theatres")
    public List<TheatreDto> getTheatreDtos() {
        return theatreDtos;
    }

    public void setTheatreDtos(List<TheatreDto> theatreDtos) {
        this.theatreDtos = theatreDtos;
    }
}
