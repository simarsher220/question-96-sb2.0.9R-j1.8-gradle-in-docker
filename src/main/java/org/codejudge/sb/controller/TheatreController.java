package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.dto.TheatreDto;
import org.codejudge.sb.error.exception.GenericException;
import org.codejudge.sb.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

//    @GetMapping(value = "/theatres/")
//    public TheatresDto getAllTheatresInCity(@RequestParam("city") String city) throws GenericException {
//        return theatreService.getAllTheatresInCity(city);
//    }
//
//    @GetMapping(value = "/theatres/{movieId}")
//    public List<TheatreDto> getTheatreByMovieId(@PathVariable("movieId") Integer movieId) throws GenericException {
//        return theatreService.getTheatresByMovieId(movieId);
//    }

    @PostMapping(value = "/theatres/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Collaborate a theatre with our platform")
    public TheatreDto addNewTheatre(@RequestBody TheatreDto theatreDto) throws GenericException {
		return theatreService.addTheatre(theatreDto);
    }

//    @DeleteMapping(value = "/theatres/{theatreId}/delete")
//    public void deleteTheatre(@PathVariable("theatreId") Integer theatreId) throws GenericException {
//        theatreService.removeTheatre(theatreId);
//    }

}
