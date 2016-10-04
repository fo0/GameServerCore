package com.fo0.gameserver.core;

import java.io.FileReader;
import java.util.List;

import com.fo0.gameserver.model.GameModelConfig;
import com.fo0.gameserver.utils.CONSTANTS;
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
			e.printStackTrace();
		}

		return games;
	}

}
