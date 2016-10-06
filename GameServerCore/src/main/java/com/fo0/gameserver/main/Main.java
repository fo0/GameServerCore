package com.fo0.gameserver.main;

import com.fo0.gameserver.controller.ControllerGameConfig;
import com.fo0.gameserver.controller.ControllerRESTServer;
import com.fo0.ss.logger.Logger;

public class Main {

	public static void main(String[] args) {

		bootstrap();

		ControllerGameConfig.readGames().forEach(e -> Logger.log.info(e.toString()));

	}

	public static void bootstrap() {
		ControllerRESTServer.start();
	}

}
