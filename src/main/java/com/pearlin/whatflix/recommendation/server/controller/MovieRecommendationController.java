package com.pearlin.whatflix.recommendation.server.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pearlin.whatflix.recommendation.service.RecomendationService;

@RestController
@RequestMapping(value = "/movies")
public class MovieRecommendationController {

	private static final Logger logger = LoggerFactory.getLogger(MovieRecommendationController.class);

	@Autowired
	private RecomendationService recommendationService;

	@RequestMapping("/user/{userId}/search")
	public ResponseEntity<List<Map>> recommendMovies(@PathVariable("userId") long userId,
			@RequestParam("text") String searchTerm,
			@RequestParam(required = false, defaultValue = "3", name = "count") int count) {
		logger.debug("userId : " + userId + " text :" + searchTerm + " count " + count);
		try {
			List<Map> movies = recommendationService.findMovies(userId, searchTerm, count);
			return new ResponseEntity<List<Map>>(movies, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while finding recommended movies for user " + userId + " searching " + searchTerm
					+ " count " + count);
		}
		return new ResponseEntity<List<Map>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}