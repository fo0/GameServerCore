package com.fo0.gameserver.model;

import com.google.gson.Gson;

public class GameModelConfig {

	private String name;
	private String startscript;
	private String stopscript;

	public GameModelConfig parseJson(String json) {
		Gson gson = new Gson();
		GameModelConfig model = gson.fromJson(json, GameModelConfig.class);
		setName(model.getName());
		setStartscript(model.getStartscript());
		setStopscript(model.getStopscript());
		return model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartscript() {
		return startscript;
	}

	public void setStartscript(String startscript) {
		this.startscript = startscript;
	}

	public String getStopscript() {
		return stopscript;
	}

	public void setStopscript(String stopscript) {
		this.stopscript = stopscript;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startscript == null) ? 0 : startscript.hashCode());
		result = prime * result + ((stopscript == null) ? 0 : stopscript.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startscript == null) {
			if (other.startscript != null)
				return false;
		} else if (!startscript.equals(other.startscript))
			return false;
		if (stopscript == null) {
			if (other.stopscript != null)
				return false;
		} else if (!stopscript.equals(other.stopscript))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameModelConfig [" + (name != null ? "name=" + name + ", " : "")
				+ (startscript != null ? "startscript=" + startscript + ", " : "")
				+ (stopscript != null ? "stopscript=" + stopscript : "") + "]";
	}

}
