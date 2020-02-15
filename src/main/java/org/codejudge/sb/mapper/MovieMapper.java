package org.codejudge.sb.mapper;

import org.codejudge.sb.dto.MovieDto;
import org.codejudge.sb.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
   
    public static List<MovieDto> getMovieDtosFromMovies(List<Movie> movies) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(getMovieDtoFromMovie(movie));
        }
        return movieDtos;
    }

    public static MovieDto getMovieDtoFromMovie(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getMovieId());
        movieDto.setMovieName(movie.getMovieName().trim());
        movieDto.setMovieOverview(movie.getMovieOverview());
        movieDto.setMoviePoster(movie.getMoviePoster());
        movieDto.setMovieTrailer(movie.getMovieTrailer());
        movieDto.setLength(movie.getLength());
        return movieDto;
    }

    public static Movie getMovieFromMovieDto(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setMovieName(movieDto.getMovieName().trim());
        movie.setMovieOverview(movieDto.getMovieOverview());
        movie.setMoviePoster(movieDto.getMoviePoster());
        movie.setMovieTrailer(movieDto.getMovieTrailer());
        movie.setLength(movieDto.getLength());
        return movie;
    }
}
