package com.fo0.gameserver.rest;

import java.util.List;

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
	public Response test(String post) {
		System.out.println("Parameters: " + post);

		if (post != null)
			Response.noContent().build();

		String[] args = post.split(",");

		GAMESERVER_COMMAND cmd = null;
		GameModelConfig game = ControllerGameConfig.getScriptPathOfGame(args[0]);

		if (game == null)
			Response.noContent().build();

		try {
			cmd = GAMESERVER_COMMAND.valueOf(args[1]);
		} catch (Exception e) {
			Logger.log.error("Server API Comnmand not found: " + args[1] + " | " + e);
			return Response.status(Status.NOT_FOUND).build();
		}

		List<String> result = null;

		switch (cmd) {
		case START:
			result = Commander.execute(true, game.getStartscript());
			break;

		case STOP:
			result = Commander.execute(true, game.getStartscript());
			break;

		case INFO:
			Logger.log.warn(LOGSTATE.NOT_IMPLEMENTED);
			break;

		case RESTART:
			result = Commander.execute(true, game.getStopscript());
			result = Commander.execute(true, game.getStartscript());
			break;

		default:
			Logger.log.warn(LOGSTATE.NOT_IMPLEMENTED + cmd.toString());
			break;
		}

		if (result == null)
			Response.serverError().build();

		StringBuffer buffer = new StringBuffer();
		result.forEach(e -> buffer.append(e + " \n "));
		return Response.ok().entity(buffer.toString()).build();
	}

}
