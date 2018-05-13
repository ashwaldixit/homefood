package com.homefood.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Address;
import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.AddressService;
import com.homefood.service.UserAuthenticationTokenService;
import com.homefood.service.UserService;

@Path("/address")
public class AddressResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	AddressService addressService;

	@Autowired
	UserService userService;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@GET
	@Path("/{addressID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByAddressID(@PathParam("addressID") long addressID) {
		return Response.status(Status.OK).entity(addressService.readById(addressID)).build();
	}

	@GET
	@Path("/activebyuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByActiveByuserID() {
		return Response.status(Status.OK).entity(addressService.getActiveAndDefaultByUser(getUser())).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAddress(Address address) {
		return Response.status(Status.OK).entity(addressService.validateAndCreate(address)).build();
	}

	@PUT
	@Path("/default")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setAsDefault(Address address) {
		return Response.status(Status.OK).entity(addressService.setAsDefault(address)).build();
	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByUser() {
		return Response.status(Status.OK).entity(addressService.getAllByUser(getUser())).build();
	}

}
