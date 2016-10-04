package com.fo0.gameserver.model;

import com.google.gson.Gson;

public class GameModelConfig {

	private String gameName;
	private String startScript;
	private String stopScript;

	public GameModelConfig parseJson(String json) {
		Gson gson = new Gson();
		GameModelConfig model = gson.fromJson(json, GameModelConfig.class);
		setGameName(model.getGameName());
		setStartScript(model.getStartScript());
		setStopScript(model.getStopScript());
		return model;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getStartScript() {
		return startScript;
	}

	public void setStartScript(String startScript) {
		this.startScript = startScript;
	}

	public String getStopScript() {
		return stopScript;
	}

	public void setStopScript(String stopScript) {
		this.stopScript = stopScript;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
		result = prime * result + ((startScript == null) ? 0 : startScript.hashCode());
		result = prime * result + ((stopScript == null) ? 0 : stopScript.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GameModelConfig))
			return false;
		GameModelConfig other = (GameModelConfig) obj;
		if (gameName == null) {
			if (other.gameName != null)
				return false;
		} else if (!gameName.equals(other.gameName))
			return false;
		if (startScript == null) {
			if (other.startScript != null)
				return false;
		} else if (!startScript.equals(other.startScript))
			return false;
		if (stopScript == null) {
			if (other.stopScript != null)
				return false;
		} else if (!stopScript.equals(other.stopScript))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameModelConfig [" + (gameName != null ? "gameName=" + gameName + ", " : "")
				+ (startScript != null ? "startScript=" + startScript + ", " : "")
				+ (stopScript != null ? "stopScript=" + stopScript : "") + "]";
	}

}
