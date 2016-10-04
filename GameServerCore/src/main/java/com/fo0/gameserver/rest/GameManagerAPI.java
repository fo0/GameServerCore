package com.fo0.gameserver.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fo0.gameserver.enums.GAMESERVER_COMMAND;
import com.fo0.gameserver.utils.Commander;
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
		System.out.println("Parameters: "+post);
		
		if (post != null)
			Response.serverError().build();

		String[] args = post.split(",");
		
		GAMESERVER_COMMAND cmd = null;
		String game = args[0];

		try {
			cmd = GAMESERVER_COMMAND.valueOf(args[1]);
		} catch (Exception e) {
			Logger.log.error("Server API Comnmand not found: " + args[1] + " | " + e);
			return Response.status(Status.NOT_FOUND).build();
		}
		
		

//		Commander.command("PATH");

		return Response.ok().build();
	}

}
