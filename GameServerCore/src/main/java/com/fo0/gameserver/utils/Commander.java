package com.fo0.gameserver.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fo0.ss.logger.Logger;

public class Commander {

	public static List<String> execute(boolean shell, String cmd) {
		List<String> listOutput = null;
		List<String> listError = null;

		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.toLowerCase().contains("window")) {
			if (shell)
				processBuilder = new ProcessBuilder("cmd", "/c", cmd);
			else
				processBuilder = new ProcessBuilder(cmd);
		} else {
			if (shell)
				processBuilder = new ProcessBuilder("/bin/bash", "-c", cmd);
			else
				processBuilder = new ProcessBuilder(cmd);

		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		// processBuilder.redirectErrorStream(true);

		try {
			Process process = processBuilder.start();

			// stdout
			InputStream stdout = process.getInputStream();
			listOutput = getStringListFromStream(stdout);
			stdout.close();

			// errout
			InputStream errout = process.getErrorStream();
			listError = getStringListFromStream(errout);
			errout.close();

			if (listError != null && !listError.isEmpty())
				return listError;

			if (listOutput != null && !listOutput.isEmpty())
				return listOutput;

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + cmd + " | " + e);
		}
		return listOutput;
	}

	public static void executeNoWait(boolean shell, String cmd) {
		List<String> listOutput = null;
		List<String> listError = null;

		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.toLowerCase().contains("window")) {
			if (shell)
				processBuilder = new ProcessBuilder("cmd", "/c", cmd);
			else
				processBuilder = new ProcessBuilder(cmd);
		} else {
			if (shell)
				processBuilder = new ProcessBuilder("/bin/bash", "-c", cmd);
			else
				processBuilder = new ProcessBuilder(cmd);

		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		// processBuilder.redirectErrorStream(true);

		try {
			Process process = processBuilder.start();

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + cmd + " | " + e);
		}
	}

	public static List<String> execute(boolean shell, List<String> cmds) {
		List<String> listOutput = null;
		List<String> listError = null;
		List<String> cmd = new ArrayList<String>();
		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.toLowerCase().contains("window")) {
			if (shell) {
				cmd.add("cmd");
				cmd.add("/c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);

		} else {
			if (shell) {
				cmd.add("/bin/bash");
				cmd.add("-c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);
		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		// processBuilder.redirectErrorStream(true);

		try {
			Process process = processBuilder.start();

			// stdout
			InputStream stdout = process.getInputStream();
			listOutput = getStringListFromStream(stdout);
			stdout.close();

			// errout
			InputStream errout = process.getErrorStream();
			listError = getStringListFromStream(errout);
			errout.close();

			if (listError != null && !listError.isEmpty())
				return listError;

			if (listOutput != null && !listOutput.isEmpty())
				return listOutput;

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + cmd + " | " + e);
		}
		return null;

	}

	public static void executeNoWait(boolean shell, List<String> cmds) {
		List<String> listOutput = null;
		List<String> listError = null;
		List<String> cmd = new ArrayList<String>();
		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.toLowerCase().contains("window")) {
			if (shell) {
				cmd.add("cmd");
				cmd.add("/c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);

		} else {
			if (shell) {
				cmd.add("/bin/bash");
				cmd.add("-c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);
		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		try {
			Process process = processBuilder.start();

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + cmd + " | " + e);
		}

	}

	public static boolean simpleExecute(boolean shell, List<String> cmds) {
		List<String> listOutput = null;
		List<String> listError = null;
		List<String> cmd = new ArrayList<String>();
		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");

		if (operatingSystem.toLowerCase().contains("window")) {
			if (shell) {
				cmd.add("cmd");
				cmd.add("/c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);

		} else {
			if (shell) {
				cmd.add("/bin/bash");
				cmd.add("-c");
			}
			cmd.addAll(cmds);
			processBuilder = new ProcessBuilder(cmd);
		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		// processBuilder.redirectErrorStream(true);

		try {
			Process process = processBuilder.start();

			// stdout
			InputStream stdout = process.getInputStream();
			listOutput = getStringListFromStream(stdout);
			stdout.close();

			// errout
			InputStream errout = process.getErrorStream();
			listError = getStringListFromStream(errout);
			errout.close();

			if (listError != null && !listError.isEmpty()) {
				Logger.log.error("Detected Errors");
				listError.forEach(e -> Logger.log.error(e));
				return false;
			}

			if (listOutput != null && !listOutput.isEmpty()) {
				Logger.log.info("Executed Successfull");
				listOutput.forEach(e -> Logger.log.info(e));
				return true;
			}

			Logger.log.info("Nothing detected - decide as success");
			return true;

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + cmd + " | " + e);
		}
		return false;

	}

	public static boolean simpleExecuteScript(String scriptPath) {
		List<String> listOutput = null;
		List<String> listError = null;

		System.out.println(new Date() + ": Recieved command: " + scriptPath);
		String tcpdumpCmdResponse = "";
		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.toLowerCase().contains("window")) {
			processBuilder = new ProcessBuilder(new String[] { "cmd.exe", "/c", "start", scriptPath });
		}

		processBuilder.directory(new File(System.getProperty("user.home")));

		try {
			Process process = processBuilder.start();

			// stdout
			InputStream stdout = process.getInputStream();
			listOutput = getStringListFromStream(stdout);
			stdout.close();

			// errout
			InputStream errout = process.getErrorStream();
			listError = getStringListFromStream(errout);
			errout.close();

			if (listError != null && !listError.isEmpty()) {
				Logger.log.error("Detected Errors");
				listError.forEach(e -> Logger.log.error(e));
				return false;
			}

			if (listOutput != null && !listOutput.isEmpty()) {
				Logger.log.info("Executed Successfull");
				listOutput.forEach(e -> Logger.log.info(e));
				return true;
			}

			Logger.log.info("Nothing detected - decide as success");
			return true;

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + scriptPath + " | " + e);
		}
		return false;

	}

	public static boolean simpleExecuteServiceKill(String service) {
		System.out.println(new Date() + ": Recieved command: " + service);

		List<String> listOutput = null;
		List<String> listError = null;

		List<String> tcpdumpCmdResponse = null;
		ProcessBuilder processBuilder = null;

		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.toLowerCase().contains("window")) {
			processBuilder = new ProcessBuilder(new String[] { "cmd.exe", "/c", "TASKKILL", "/IM", service, "/F" });
		}
		processBuilder.directory(new File(System.getProperty("user.home")));

		try {
			Process process = processBuilder.start();

			// stdout
			InputStream stdout = process.getInputStream();
			listOutput = getStringListFromStream(stdout);
			stdout.close();

			// errout
			InputStream errout = process.getErrorStream();
			listError = getStringListFromStream(errout);
			errout.close();

			if (listError != null && !listError.isEmpty()) {
				Logger.log.error("Detected Errors");
				listError.forEach(e -> Logger.log.error(e));
				return false;
			}

			if (listOutput != null && !listOutput.isEmpty()) {
				Logger.log.info("Executed Successfull");
				listOutput.forEach(e -> Logger.log.info(e));
				return true;
			}

			Logger.log.info("Nothing detected - decide as success");
			return true;

		} catch (Exception e) {
			Logger.log.error("ERROR in Commander | Cmd: " + service + " | " + e);
		}
		return false;
	}

	private static List<String> getStringListFromStream(InputStream stream) throws IOException {
		if (stream != null) {
			List<String> list = new ArrayList<String>();

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					list.add(line);
				}

			} finally {
				stream.close();
			}
			return list;
		} else {
			return null;
		}
	}

}