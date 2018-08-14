package com.pearlin.whatflix.recommendation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pearlin.whatflix.recommendation.service.entities.SearchAndUserPreferences;

@Service
public class MovieDetailsClient {

	private static final Logger logger = LoggerFactory.getLogger(MovieDetailsClient.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${services.movie-service.host}")
	private String movieServiceHost;

	private static final String searchEndPoint = "/movies/search";

	public List<Map> getMoviesForPreference(List<String> favDirectors, List<String> favActors,
			List<String> languages, String search, int count) {		
		List<Map> recommendedMovies = new ArrayList<>(0);
		SearchAndUserPreferences entity = new SearchAndUserPreferences();
		entity.setSearchTerm(search);
		entity.setFavouriteActors(favActors);
		entity.setFavouriteDirectors(favDirectors);
		entity.setPreferredLanguages(languages);
		entity.setRequiredNoMovies(count);
		String requestUrl = movieServiceHost + searchEndPoint;
		try {
			logger.info(requestUrl);
			ResponseEntity<List> response = restTemplate.postForEntity(requestUrl, entity, List.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				recommendedMovies = (List<Map>) response.getBody();
				logger.info("Recommended movies : " + recommendedMovies);
			} else {
				throw new Exception("Error while retrieving recommended movies for " + entity + " from " + requestUrl
						+ " with " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error while retrieving recommended movies for " + entity + " from " + requestUrl, e);
		}
		return recommendedMovies;
	}
}
