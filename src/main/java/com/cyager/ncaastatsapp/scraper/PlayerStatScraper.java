package com.cyager.ncaastatsapp.scraper;

import com.cyager.ncaastatsapp.model.PlayerSearchResults;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;

public class PlayerStatScraper {

	private static String playerSearchUrlTemplate = "https://stats.ncaa.org/search/players/data?sEcho=1&sSearch=%s&bRegex=false&sport_code_filter=MLA";

	ObjectMapper mapper = new ObjectMapper();

	public String getPlayerStatsLink(String playerName) throws IOException, InterruptedException {
		System.out.println("Calling getPlayerStatsLink in service layer with parameter " + playerName);
		playerName = URLEncoder.encode(playerName, StandardCharsets.UTF_8.toString());
		URL url;
		try {
			url = new URI(String.format(playerSearchUrlTemplate, playerName)).toURL();
		} catch (URISyntaxException e) {
			System.out.println("error parsing URI: " + e);
			return "Failure";
		}
		System.out.println("URL: " + url);
		
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuilder builder = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			builder.append(inputLine);
		in.close();
		String responseAsString = builder.toString();
		System.out.println("responseAsString: " + responseAsString);
		PlayerSearchResults results;
		try {
			results = deserializePlayerSearch(responseAsString);
		} catch(JsonProcessingException e) {
			System.out.println("error parsing json: " + e);
			return "Failure";
		}
		System.out.println(results);
		return(results.aaData[0].personalStatsLink);
	}

	private PlayerSearchResults deserializePlayerSearch(String response) throws JsonProcessingException {
		PlayerSearchResults results = mapper.readValue(response, PlayerSearchResults.class);
		return results;
	}
}
