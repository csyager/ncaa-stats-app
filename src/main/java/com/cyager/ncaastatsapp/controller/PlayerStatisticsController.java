package com.cyager.ncaastatsapp.controller;

import com.cyager.ncaastatsapp.service.PlayerStatisticsService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.http.HttpResponse;

@RestController
@RequestMapping(value = "/player")
public class PlayerStatisticsController {

	@Autowired
	private PlayerStatisticsService playerStatisticsService;
	
	@GetMapping
	public String getPlayerStats(@RequestParam String playerName) {
		System.out.println("calling getPlayerStats with parameter " + playerName);
		String response =  playerStatisticsService.getPlayerStats(playerName);
		System.out.println("response: " + response);
		return response;
	}
}

