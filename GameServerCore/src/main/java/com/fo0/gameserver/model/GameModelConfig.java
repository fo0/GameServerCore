package com.fo0.gameserver.model;

import java.io.Serializable;

import com.google.gson.Gson;

public class GameModelConfig implements Serializable {

	private String name = "";
	private String user = "admin";
	private String startscript = "";
	private String stopscript = "";
	private String alivscript = "";

	public GameModelConfig parseJson(String json) {
		Gson gson = new Gson();
		GameModelConfig model = gson.fromJson(json, GameModelConfig.class);
		if (model == null)
			return null;

		setName(model.getName());
		setUser(model.getUser());
		setStartscript(model.getStartscript());
		setStopscript(model.getStopscript());
		setAlivscript(model.getAlivscript());
		return model;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAlivscript() {
		return alivscript;
	}

	public void setAlivscript(String alivscript) {
		this.alivscript = alivscript;
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
		result = prime * result + ((alivscript == null) ? 0 : alivscript.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startscript == null) ? 0 : startscript.hashCode());
		result = prime * result + ((stopscript == null) ? 0 : stopscript.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (alivscript == null) {
			if (other.alivscript != null)
				return false;
		} else if (!alivscript.equals(other.alivscript))
			return false;
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameModelConfig [" + (name != null ? "name=" + name + ", " : "")
				+ (user != null ? "user=" + user + ", " : "")
				+ (startscript != null ? "startscript=" + startscript + ", " : "")
				+ (stopscript != null ? "stopscript=" + stopscript + ", " : "")
				+ (alivscript != null ? "alivscript=" + alivscript : "") + "]";
	}

}
