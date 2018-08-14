package com.pearlin.whatflix.recommendation.service.entities;

import java.util.List;

public class UserPreferencesEntity {

	private long userId;
	private List<String> preferredLanguages;
	private List<String> favActors;
	private List<String> favDirectors;
	private List<String> searched;

	public UserPreferencesEntity() {
	}

	public UserPreferencesEntity(long userId) {
		this.userId = userId;
	}

	public List<String> getPreferredLanguages() {
		return preferredLanguages;
	}

	public void setPreferredLanguages(List<String> preferredLanguages) {
		this.preferredLanguages = preferredLanguages;
	}

	public List<String> getFavActors() {
		return favActors;
	}

	public void setFavActors(List<String> favActors) {
		this.favActors = favActors;
	}

	public List<String> getFavDirectors() {
		return favDirectors;
	}

	public void setFavDirectors(List<String> favDirectors) {
		this.favDirectors = favDirectors;
	}

	public List<String> getSearched() {
		return searched;
	}

	public void setSearched(List<String> searched) {
		this.searched = searched;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "UserPreferencesEntity [userId=" + userId + ", preferredLanguages=" + preferredLanguages + ", favActors="
				+ favActors + ", favDirectors=" + favDirectors + ", searched=" + searched + "]";
	}

}