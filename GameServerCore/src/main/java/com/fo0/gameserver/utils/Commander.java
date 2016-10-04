package com.fo0.gameserver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

public class Commander {
	public static String command(String startOrStop, boolean waitForResult) {
		System.out.println(new Date() + ": Recieved command: " + startOrStop);
		String tcpdumpCmdResponse = "";
		ProcessBuilder crunchifyProcessBuilder = null;

		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.toLowerCase().contains("window")) {
			crunchifyProcessBuilder = new ProcessBuilder(
					new String[] { "cmd.exe", "/c", "start", "C:/Users/space/Desktop/" + startOrStop + ".lnk" });
		}
		crunchifyProcessBuilder.redirectErrorStream(true);
		try {
			Process process = crunchifyProcessBuilder.start();
			if (waitForResult) {
				InputStream crunchifyStream = process.getInputStream();
				tcpdumpCmdResponse = getStringFromStream(crunchifyStream);
				crunchifyStream.close();
			}
		} catch (Exception e) {
			System.out.println("Processing cmd failed " + e);
		}
		System.out.println(new Date() + "Finished command");
		return tcpdumpCmdResponse;
	}

	public static String commandKill(String service, boolean waitForResult) {
		System.out.println(new Date() + ": Recieved command: " + service);
		String tcpdumpCmdResponse = "";
		ProcessBuilder crunchifyProcessBuilder = null;

		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.toLowerCase().contains("window")) {
			crunchifyProcessBuilder = new ProcessBuilder(
					new String[] { "cmd.exe", "/c", "TASKKILL", "/IM", service, "/F" });
		}
		crunchifyProcessBuilder.redirectErrorStream(true);
		try {
			Process process = crunchifyProcessBuilder.start();
			if (waitForResult) {
				InputStream crunchifyStream = process.getInputStream();
				tcpdumpCmdResponse = getStringFromStream(crunchifyStream);
				crunchifyStream.close();
			}
		} catch (Exception e) {
			System.out.println("Processing cmd failed " + e);
		}
		System.out.println(new Date() + "Finished command");
		return tcpdumpCmdResponse;
	}

	private static String getStringFromStream(InputStream crunchifyStream) throws IOException {
		System.out.println("inside getStringFromStream()");
		if (crunchifyStream != null) {
			Writer crunchifyWriter = new StringWriter();

			char[] crunchifyBuffer = new char[2048];
			try {
				Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
				int count;
				while ((count = crunchifyReader.read(crunchifyBuffer)) != -1) {
					crunchifyWriter.write(crunchifyBuffer, 0, count);
				}
			} finally {
				crunchifyStream.close();
			}
			return crunchifyWriter.toString();
		} else {
			return "";
		}
	}
}