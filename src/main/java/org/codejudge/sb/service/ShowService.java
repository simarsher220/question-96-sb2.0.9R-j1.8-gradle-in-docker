package org.codejudge.sb.service;

import org.codejudge.sb.dao.MovieRepository;
import org.codejudge.sb.dao.ShowRepository;
import org.codejudge.sb.dao.TheatreRepository;
import org.codejudge.sb.dto.*;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.entity.Show;
import org.codejudge.sb.entity.Theatre;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.mapper.MovieMapper;
import org.codejudge.sb.mapper.ShowMapper;
import org.codejudge.sb.mapper.TheatreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public ShowsDto addShow(ShowDtoRequest showDto) throws GenericException {
        Movie movie = null;
        Theatre theatre = null;
        List<Show> shows = null;
        try {
            movie = movieRepository.findMovieById(showDto.getMovieId());
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }
            theatre = theatreRepository.findTheatreById(showDto.getTheatreId());
            if (theatre == null) {
                throw new Exception("Theatre not found!!");
            }
            shows = showRepository.findByTheatre(theatre);
            if (shows == null) {
                throw new Exception("Shows not found!!");
            }
            if (shows != null && shows.size() > 0) {
                for (Show show : shows) {
                    LocalTime time = showDto.getTime().toLocalTime();
                    LocalTime compTime = show.getId().getTime().toLocalTime();
                    if (MINUTES.between(time, compTime) == 0) {
                        throw new Exception("Time not suitable");
                    }
                    if (MINUTES.between(time, compTime) < 0 && Math.abs(MINUTES.between(time, compTime)) - show.getMovie().getLength() < 0) {
                        throw new Exception("Time not suitable");
                    }    // -ve
                    if(MINUTES.between(time, compTime) > 0 && Math.abs(MINUTES.between(time, compTime)) - movie.getLength() < 0) {
                        throw new Exception("Time not suitable");
                    }
                }
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Show show = ShowMapper.getShowFromShowDtoRequest(movie, theatre, showDto.getDate(), showDto.getTime());
        try {
            show = showRepository.saveAndFlush(show);
            if (show == null) {
                throw new Exception("Couldn't insert show!!");
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ShowsDto showsDto = new ShowsDto();
        showsDto.setMovie(MovieMapper.getMovieDtoFromMovie(show.getMovie()));
        showsDto.setTheatre(TheatreMapper.getTheatreDtoFromTheatre(show.getTheatre()));
        showsDto.setShowDtos(ShowMapper.getShowsDtoFromShows(Collections.singletonList(show)));
        return showsDto;
    }

    public void deleteShow(ShowDtoRequest showDto) throws GenericException {
        Movie movie = null;
        Theatre theatre = null;
        try {
            movie = movieRepository.findMovieById(showDto.getMovieId());
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }
            theatre = theatreRepository.findTheatreById(showDto.getTheatreId());
            if (theatre == null) {
                throw new Exception("Movie not found!!");
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Show show = ShowMapper.getShowFromShowDtoRequest(movie, theatre, showDto.getDate(), showDto.getTime());
        try {
            showRepository.delete(show);
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<ShowDto> getShowListByMovieAndTheatreAndDate(Integer movieId, Integer theatreId, Date date) throws GenericException {
        List<Show> showFilter = new ArrayList<>();
        try {
            Movie movie = movieRepository.findMovieById(movieId);
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }
            Theatre theatre = theatreRepository.findTheatreById(theatreId);
            if (theatre == null) {
                throw new Exception("Movie not found!!");
            }
            List<Show> shows = showRepository.findByMovieAndTheatre(movie, theatre);
            if (shows == null) {
                throw new Exception("Movie not found!!");
            }
            for (Show show : shows) {
                Date compDate = show.getId().getDate();
                if (compDate.equals(date)) {
                    showFilter.add(show);
                }
            }
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ShowMapper.getShowsDtoFromShows(showFilter);
    }

    public ShowsDto getShowsByMovieAndTheatreAndDate(Integer movieId, Integer theatreId, Date date) throws GenericException {
        ShowsDto showsDto = new ShowsDto();
        try {
            Movie movie = movieRepository.findMovieById(movieId);
            if (movie == null) {
                throw new Exception("Movie not found");
            }
            Theatre theatre = theatreRepository.findTheatreById(theatreId);
            if (theatre == null) {
                throw new Exception("Movie not found");
            }
            showsDto.setMovie(MovieMapper.getMovieDtoFromMovie(movie));
            showsDto.setTheatre(TheatreMapper.getTheatreDtoFromTheatre(theatre));
            showsDto.setShowDtos(getShowListByMovieAndTheatreAndDate(movieId, theatreId, date));
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return showsDto;
    }

    public TheatresShowsDto getShowsByMovieAndCityAndDate(Integer movieId, String city, Date date) throws GenericException {

        TheatresShowsDto theatresShowsDto = new TheatresShowsDto();
        List<TheatreShowsDto> theatreShowsDtos = new ArrayList<>();
        try {
            Movie movie = movieRepository.findMovieById(movieId);
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }
            List<Theatre> theatres = theatreRepository.findAllByCity(city);
            if (theatres == null) {
                throw new Exception("Theatres not found!!");
            }
            for (Theatre theatre : theatres) {
                TheatreShowsDto theatreShowsDto = new TheatreShowsDto();
                theatreShowsDto.setTheatreId(theatre.getTheatreId());
                theatreShowsDto.setTheatreLocation(theatre.getTheatreLocation());
                theatreShowsDto.setTheatreName(theatre.getTheatreName());
                theatreShowsDto.setCity(theatre.getCity());
                theatreShowsDto.setPincode(theatre.getPincode());
                theatreShowsDto.setShowDtos(getShowListByMovieAndTheatreAndDate(movieId, theatre.getTheatreId(), date));
                theatreShowsDtos.add(theatreShowsDto);
            }
            theatresShowsDto.setMovie(MovieMapper.getMovieDtoFromMovie(movie));
            theatresShowsDto.setTheatreShowsDtos(theatreShowsDtos);
        }
        catch (Exception e) {
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return theatresShowsDto;
    }
}
