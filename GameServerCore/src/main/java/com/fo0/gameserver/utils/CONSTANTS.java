package com.fo0.gameserver.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.fo0.ss.logger.Logger;

public class CONSTANTS {

	public static String GAMES_CONFIG_PATH = "";
	public static String PATH_CONFIG = "";

	static {
		PATH_CONFIG = System.getProperty("user.dir") + "/config/config.properties";

		if (!new File(System.getProperty("user.dir") + "/config/").exists()) {
			new File(System.getProperty("user.dir") + "/config/").mkdirs();
		}

		if (!new File(PATH_CONFIG).exists()) {

			// ########### Create DEFAULT properties ############
			try (OutputStream out = new FileOutputStream(PATH_CONFIG)) {
				Properties properties = new Properties();

				// Database Settings
				properties.setProperty("gamescfg", "config/games.json");

				properties.store(out, "Storing Properties");

				loadProperties(properties);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			// ########### Reading properties ###########
			try (InputStream in = new FileInputStream(PATH_CONFIG)) {
				Properties prop = new Properties();
				prop.load(in);

				loadProperties(prop);

				// CLI

			} catch (IOException e) {
				// TODO Auto-generated catch block
				Logger.log.error("Failed to load Properties " + e);
			}
		}

	}

	private static void loadProperties(Properties prop) {
		// Database
		GAMES_CONFIG_PATH = prop.getProperty("gamescfg");
	}

}