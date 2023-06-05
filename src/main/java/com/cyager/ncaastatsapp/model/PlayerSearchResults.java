package com.cyager.ncaastatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerSearchResults {

	public PersonalStatsLinkObject[] aaData;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PersonalStatsLinkObject {
		@JsonProperty("people-last_name")
		public String personalStatsLink;
	}
}
