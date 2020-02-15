package org.codejudge.sb.service;

import org.codejudge.sb.dao.MovieRepository;
import org.codejudge.sb.dao.ShowRepository;
import org.codejudge.sb.dao.TheatreRepository;
import org.codejudge.sb.dto.MovieDto;
import org.codejudge.sb.dto.MoviesDto;
import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.entity.Show;
import org.codejudge.sb.entity.Theatre;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ShowRepository showRepository;

    public MoviesDto getAllMovies() throws GenericException {
        List<Movie> movies = null;
        try {
            movies = movieRepository.findAll();
            if (movies == null) {
                throw new Exception("Movies not found!!");
            }
        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        List<MovieDto> movieDtos = MovieMapper.getMovieDtosFromMovies(movies);
        MoviesDto moviesDto = new MoviesDto();
        moviesDto.setMovies(movieDtos);
        return moviesDto;
    }

    public MovieDto getMovieById(Integer movieId) throws GenericException {
        Movie movie = null;
        try {
            movie = movieRepository.findMovieById(movieId);
            if (movie == null) {
                throw new Exception("Movie not found!!");
            }

        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return MovieMapper.getMovieDtoFromMovie(movie);
    }

    public MovieDto addMovie(MovieDto movieDto) throws GenericException {
        Movie movie = MovieMapper.getMovieFromMovieDto(movieDto);
        try {
            movie = movieRepository.saveAndFlush(movie);
        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return MovieMapper.getMovieDtoFromMovie(movie);
    }

    public void removeMovie(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

    public MoviesDto getMoviesByTheatre(Integer theatreId) throws GenericException {
        List<Movie> movies = new ArrayList<>();
        Theatre theatre = null;
        List<Show> shows = null;
        try {
            theatre = theatreRepository.findTheatreById(theatreId);
            if (theatre == null) {
                throw new Exception("Theatre not found!!");
            }
            shows = showRepository.findAllByTheatre(theatre);
            if (shows == null) {
                throw new Exception("Shows not found!!");
            }
        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        for (Show show : shows) {
            if (!movies.contains(show.getMovie())) {
                movies.add(show.getMovie());
            }
        }
        MoviesDto moviesDto = new MoviesDto();
        moviesDto.setMovies(MovieMapper.getMovieDtosFromMovies(movies));
        return moviesDto;
    }

    public MoviesDto getMoviesByCity(String city) throws GenericException {
        List<Movie> movies = new ArrayList<>();
        try {
            List<Theatre> theatres = theatreRepository.findAllByCity(city);
            if (theatres == null) {
                throw new Exception("Theatres not found!!");
            }
            for (Theatre theatre : theatres) {
                List<Show> shows = showRepository.findAllByTheatre(theatre);
                if (shows == null) {
                    throw new Exception("Shows not found!!");
                }
                for (Show show : shows) {
                    if (!movies.contains(show.getMovie())) {
                        movies.add(show.getMovie());
                    }
                }
            }
        }
        catch (Exception ex) {
            throw new GenericException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        MoviesDto moviesDto = new MoviesDto();
        moviesDto.setMovies(MovieMapper.getMovieDtosFromMovies(movies));
        return moviesDto;
    }
}
