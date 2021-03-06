package com.homefood.webservice;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.UserAuthenticationTokenService;
import com.homefood.service.UserService;

@Path("/customers")
public class CustomerResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	UserService customerService;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@GET
	@Path("/{customerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("customerid") long customerid) {
		return Response.ok().entity(customerService.readById(customerid)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("customerid") long customerid) {
		return Response.ok().entity(customerService.readById(getUser().getUserid())).build();
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") String name) {
		return Response.ok().entity(customerService.readByUserName(name)).build();
	}

	@GET
	@Path("/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByEmail(@PathParam("email") String email) {
		return Response.ok().entity(customerService.readByEmail(email)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(User customer) throws NoSuchAlgorithmException {
		return Response.ok().entity(customerService.validateAndCreateCustomer(customer)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(User customer) throws NoSuchAlgorithmException {
		return Response.ok().entity(customerService.update(customer)).build();
	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;
	}

}
