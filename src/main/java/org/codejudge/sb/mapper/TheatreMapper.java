package org.codejudge.sb.mapper;

import org.codejudge.sb.dto.TheatreDto;
import org.codejudge.sb.entity.Theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreMapper {
    
    public static List<TheatreDto> getTheatreDtosFromTheatres(List<Theatre> theatres) {
        List<TheatreDto> theatreDtos = new ArrayList<>();
        for (Theatre theatre : theatres) {
            theatreDtos.add(getTheatreDtoFromTheatre(theatre));
        }
        return theatreDtos;
    }

    public static TheatreDto getTheatreDtoFromTheatre(Theatre theatre) {
        TheatreDto theatreDto = new TheatreDto();
        theatreDto.setTheatreId(theatre.getTheatreId());
        theatreDto.setCity(theatre.getCity());
        theatreDto.setPincode(theatre.getPincode());
        theatreDto.setTheatreLocation(theatre.getTheatreLocation());
        theatreDto.setTheatreName(theatre.getTheatreName());
        return theatreDto;
    }

    public static Theatre getTheatreFromTheatreDto(TheatreDto theatreDto) {
        Theatre theatre = new Theatre();
        theatre.setCity(theatreDto.getCity());
        theatre.setPincode(theatreDto.getPincode());
        theatre.setTheatreLocation(theatreDto.getTheatreLocation());
        theatre.setTheatreName(theatreDto.getTheatreName());
        return theatre;
    }

    public static void createSeatsForTheatre(Theatre theatre) {

    }
}
