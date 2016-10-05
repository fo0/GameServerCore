package com.fo0.gameserver.core;

import java.io.FileReader;
import java.util.List;

import com.fo0.gameserver.model.GameModelConfig;
import com.fo0.gameserver.utils.CONSTANTS;
import com.fo0.ss.logger.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ControllerGameConfig {

	public static List<GameModelConfig> readGames() {
		Gson gson = new Gson();
		List<GameModelConfig> games = null;
		try {
			games = gson.fromJson(new FileReader(CONSTANTS.GAMES_CONFIG_PATH), new TypeToken<List<GameModelConfig>>() {
			}.getType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.log.error("Cannot find Path: " + CONSTANTS.GAMES_CONFIG_PATH + " | " + e);
			e.printStackTrace();
		}

		return games;
	}

	public static GameModelConfig getScriptPathOfGame(String game) {

		for (GameModelConfig cfg : readGames()) {
			if (cfg.getName().equalsIgnoreCase(game))
				return cfg;
		}

		return null;

	}

}
