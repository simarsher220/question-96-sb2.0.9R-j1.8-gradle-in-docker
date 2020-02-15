package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.dto.ShowDtoRequest;
import org.codejudge.sb.dto.ShowsDto;
import org.codejudge.sb.dto.TheatresShowsDto;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class ShowsController {

    @Autowired
    private ShowService showService;

    @PostMapping(value = "/shows/create")
    @ApiOperation("Add a show for a movie to the platform")
    public ShowsDto addShow(@RequestBody ShowDtoRequest showDto) throws GenericException {
        return showService.addShow(showDto);
    }

//    @DeleteMapping(value = "/shows/delete")
//    public void deleteShow(@RequestBody ShowDtoRequest showDto) throws GenericException {
//        showService.deleteShow(showDto);
//    }
//
//    @GetMapping(value = "/shows")
//    public ShowsDto getShowsByMovieTheatreDate(@RequestParam("movie_id") Integer movieId, @RequestParam("theatre_id") Integer theatreId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws GenericException {
//        return showService.getShowsByMovieAndTheatreAndDate(movieId, theatreId, date);
//    }

    @GetMapping(value = "/showsBy")
    @ApiOperation("Get all the shows for a particular movie by city and date")
    public TheatresShowsDto getShowsByMovieCityDate(@RequestParam("movie_id") Integer movieId, @RequestParam("city") String city, @RequestParam("date") Date date) throws GenericException {
        return showService.getShowsByMovieAndCityAndDate(movieId, city, date);
    }
}
