package com.fo0.gameserver.core;

public class Main {

	public static void main(String[] args) {

		bootstrap();

	}

	public static void bootstrap() {
		ControllerRESTServer.start();
	}

}
