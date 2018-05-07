package com.homefood.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.homefood.codetype.DayAvailablity;

@Path("/codetypes")
public class CodeTypeResource {

	@GET
	@Path("/availability")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailability() {
		return Response.ok().entity(DayAvailablity.values()).build();
	}

}
