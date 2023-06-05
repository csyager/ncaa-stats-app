package com.cyager.ncaastatsapp.config;

import com.cyager.ncaastatsapp.scraper.PlayerStatScraper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ScraperConfig {

	@Bean(name="playerStatScraper")
	public PlayerStatScraper playerStatScraper() {
		return new PlayerStatScraper();
	}

}
