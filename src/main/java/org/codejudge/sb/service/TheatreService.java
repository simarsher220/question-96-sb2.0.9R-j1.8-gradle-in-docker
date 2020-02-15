package org.codejudge.sb.service;


import org.codejudge.sb.dao.MovieRepository;
import org.codejudge.sb.dao.ShowRepository;
import org.codejudge.sb.dao.TheatreRepository;
import org.codejudge.sb.dto.TheatreDto;
import org.codejudge.sb.dto.TheatresDto;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.entity.Show;
import org.codejudge.sb.entity.Theatre;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.mapper.TheatreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import com.example.bookmyshow.dao.SeatRepository;
//import com.example.bookmyshow.entity.Seat;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private CityService cityService;
    
//    @Autowired
//    private SeatRepository seatRepository;

    public TheatresDto getAllTheatresInCity(String city) throws GenericException {
        List<Theatre> theatres = null;
        try {
            theatres = theatreRepository.findAllByCity(city);
            if (theatres == null) {
                throw new Exception("Theatres not found!!");
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        TheatresDto theatresDto = new TheatresDto();
        theatresDto.setTheatreDtos(TheatreMapper.getTheatreDtosFromTheatres(theatres));
        return theatresDto;
    }

    public void removeTheatre(Integer theatreId) throws GenericException {
        try {
            theatreRepository.deleteById(theatreId);
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public TheatreDto addTheatre(TheatreDto theatreDto) throws GenericException {
        Theatre theatre = TheatreMapper.getTheatreFromTheatreDto(theatreDto);
        validateCity(theatre);
        String errorMessage = "Couldn't add the theatre!!";
        try {
        	theatre = theatreRepository.saveAndFlush(theatre);
            if (theatre == null) {
            	throw new Exception(errorMessage);
            }
		} catch (Exception e) {
			throw new GenericException(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return TheatreMapper.getTheatreDtoFromTheatre(theatre);
    }

    public List<TheatreDto> getTheatresByMovieId(Integer movieId) throws GenericException {
        List<Theatre> theatres = new ArrayList<>();
        try {
            Movie movie = movieRepository.findMovieById(movieId);
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }
            List<Show> shows = showRepository.findAllByMovie(movie);
            if (shows == null) {
                throw new Exception("Shows not found!!");
            }
            for (Show show : shows) {
                if (!theatres.contains(show.getTheatre())) {
                    theatres.add(show.getTheatre());
                }
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return TheatreMapper.getTheatreDtosFromTheatres(theatres);
    }
    
    private void validateCity(Theatre theatre) throws GenericException {
		boolean validCity = false;
        String[] cities = cityService.getAllCities();
        if (cities != null && cities.length > 0) {
        	for (int i = 0; i < cities.length; i++) {
				String city = cities[i];
				if (theatre.getCity().equals(city)) {
					validCity = true;
					break;
				}
			}
        }
        if (!validCity) {
        	throw new GenericException("Invalid City!!", HttpStatus.BAD_REQUEST);
        }
	}
}
