package com.fo0.gameserver.core;

import com.ctf.rest.server.RESTServer;
import com.fo0.ss.logger.Logger;

public class ControllerRESTServer {

	  public static void start() {
	    createRESTServer();
	    Logger.log.info("[SUCCESS] Started: RESTServer");
	  }

	  private static void createRESTServer() {
	    registerAPIs();

	    RESTServer.start();
	  }

	  private static void registerAPIs() {
	    RESTServer.registerAPIContext("com.fo0.gameserver.rest");
	  }

	}