package com.pearlin.whatflix.recommendation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearlin.whatflix.recommendation.service.entities.UserPreferencesEntity;

@Service
public class RecomendationService {

	@Autowired
	private MovieDetailsClient movieDetailsClient;

	@Autowired
	private UserDetailsClient userDetailsClient;

	private static final Logger logger = LoggerFactory.getLogger(RecomendationService.class);

	public List<Map> findMovies(long userId, String searchTerm, int count) {
		List<Map> movies = new ArrayList<Map>(0);
		try {
			UserPreferencesEntity entity = userDetailsClient.getUserPreferences(userId);
			movies = movieDetailsClient.getMoviesForPreference(entity.getFavDirectors(), entity.getFavActors(),
					entity.getPreferredLanguages(), searchTerm, count);
		} catch (Exception e) {
			logger.error("Error while finding recommended movies for userId " + userId + " with the search term "
					+ searchTerm + count, e);
		}
		return movies;
	}
}
