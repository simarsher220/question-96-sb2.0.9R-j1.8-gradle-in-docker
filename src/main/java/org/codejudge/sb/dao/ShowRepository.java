package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Movie;
import org.codejudge.sb.entity.Show;
import org.codejudge.sb.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    List<Show> findAllByMovie(Movie movie);

    List<Show> findAllByTheatre(Theatre theatre);

    List<Show> findByMovieAndTheatre(Movie movie, Theatre theatre);

    List<Show> findByTheatre(Theatre theatre);
}
