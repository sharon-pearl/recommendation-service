package com.pearlin.whatflix.recommendation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pearlin.whatflix.recommendation.service.entities.UserPreferencesEntity;

@Service
public class UserDetailsClient {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsClient.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${services.user-service.host}")
	private String userServiceHost;

	private static final String GET_USER_DETAILS = "/user/getUserPreferences/";

	public UserPreferencesEntity getUserPreferences(long userId) {
		UserPreferencesEntity entity = null;
		try {
			String requestUrl = userServiceHost + GET_USER_DETAILS + userId;
			logger.info(requestUrl + userId);
			ResponseEntity<UserPreferencesEntity> response = restTemplate.getForEntity(requestUrl,
					UserPreferencesEntity.class);			
			if (response.getStatusCode() == HttpStatus.OK) {
				entity = response.getBody();
			} else {
				throw new Exception("Error while retrieving user preferences for " + userId + " from " + requestUrl
						+ " with " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error while getting user preferences for " + userId, e);
		}
		return entity != null ? entity : new UserPreferencesEntity(userId);
	}

}