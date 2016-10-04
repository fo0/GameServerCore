package com.fo0.gameserver.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fo0.gameserver.enums.GAMESERVER_COMMAND;
import com.fo0.ss.logger.Logger;

@Path(value = "/manager")
public class GameManagerAPI {

	@POST
	@Path("/command")
	@Produces(MediaType.TEXT_PLAIN)
	public Response test(String arg0) {
		GAMESERVER_COMMAND cmd = null;

		try {
			cmd = GAMESERVER_COMMAND.valueOf(arg0);
		} catch (Exception e) {
			Logger.log.error("Server API Comnmand not found: " + arg0 + " | " + e);
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity("Accepted").build();
	}

}
