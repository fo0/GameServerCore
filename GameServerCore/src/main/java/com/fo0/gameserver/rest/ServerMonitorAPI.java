package com.fo0.gameserver.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/")
public class ServerMonitorAPI {

	@GET
	@Path("/alive")
	@Produces(MediaType.TEXT_PLAIN)
	public Response alive() {
		return Response.ok().build();
	}

}
