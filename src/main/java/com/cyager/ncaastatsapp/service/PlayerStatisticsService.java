package com.cyager.ncaastatsapp.service;

import com.cyager.ncaastatsapp.scraper.PlayerStatScraper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.http.HttpResponse;
import java.io.IOException;

@Service
public class PlayerStatisticsService {
	@Autowired
	private static PlayerStatScraper playerStatScraper;

	public PlayerStatisticsService(PlayerStatScraper playerStatScraper) {
		this.playerStatScraper = playerStatScraper;
	}

	public String getPlayerStats(String playerName) {
		try {
			return playerStatScraper.getPlayerStatsLink(playerName);
		} catch(IOException e) {
			return "Caught IOException: " + e;
		} catch(InterruptedException e) {
			return "Caught InterruptedException: " + e;
		}
	}
}
