package com.fo0.gameserver.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fo0.gameserver.core.ControllerGameConfig;
import com.fo0.gameserver.enums.GAMESERVER_COMMAND;
import com.fo0.gameserver.model.GameModelConfig;
import com.fo0.gameserver.utils.Commander;
import com.fo0.ss.logger.LOGSTATE;
import com.fo0.ss.logger.Logger;

@Path(value = "/manager")
public class GameManagerAPI {

	/**
	 * 
	 * Post = GAME,COMMAND
	 * 
	 * 
	 * @param post
	 * @return
	 */
	@POST
	@Path("/command")
	@Produces(MediaType.TEXT_PLAIN)
	public Response command(String post) {
		Logger.log.debug("Parameters: " + post);

		if (post == null)
			Response.noContent().build();

		String[] args = post.split(",");

		GAMESERVER_COMMAND cmd = null;
		GameModelConfig game = ControllerGameConfig.getScriptPathOfGame(args[0]);

		if (game == null)
			Response.noContent().build();

		try {
			cmd = GAMESERVER_COMMAND.valueOf(args[1].toUpperCase());
		} catch (Exception e) {
			Logger.log.error("Server API Comnmand not found: " + args[1] + " | " + e);
			return Response.status(Status.NOT_FOUND).build();
		}

		List<String> result = new ArrayList<String>();

		switch (cmd) {
		case START:
			result.addAll(Commander.execute(true, game.getStartscript()));
			// Commander.executeNoWait(true, game.getStartscript());
			break;

		case STOP:
			result.addAll(Commander.execute(true, game.getStopscript()));
			// Commander.executeNoWait(true, game.getStopscript());
			break;

		case INFO:
			Logger.log.warn(LOGSTATE.NOT_IMPLEMENTED);
			break;

		case STATUS:
			result.addAll(Commander.execute(true, game.getAlivscript()));
			break;

		case RESTART:
			// Commander.executeNoWait(true, game.getStopscript());
			// Commander.executeNoWait(true, game.getStartscript());
			result.addAll(Commander.execute(true, game.getStopscript()));
			result.addAll(Commander.execute(true, game.getStartscript()));
			break;

		default:
			Logger.log.warn(LOGSTATE.NOT_IMPLEMENTED + cmd.toString());
			break;
		}

		Logger.log.info(LOGSTATE.PROCESSING + " Game: " + game.getName() + ":|: Command: " + cmd);

		// if (result.isEmpty())
		// Response.serverError().build();

		// StringBuffer buffer = new StringBuffer();
		// result.forEach(e -> buffer.append(e + " \n "));
		return Response.ok().build();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gamesList() {
		return Response.ok().entity(ControllerGameConfig.readGames()).build();
	}

}
