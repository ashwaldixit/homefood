package com.homefood.webservice;

import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Cart;
import com.homefood.service.CartService;
import com.homefood.service.UserService;

@Path("/cart")
public class CartResource {

	@Autowired
	CartService cartService;
	@Autowired
	UserService customerService;

	@GET
	@Path("{customerid}/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveCartDetailsOfUser(@PathParam("customerid") long customerid) throws URISyntaxException {
		return Response.status(200).entity(cartService.readAllActiveByCustomer(customerService.readById(customerid))).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertCart(Cart cart) throws URISyntaxException {
		return Response.status(200).entity(cartService.createCart(cart)).build();
	}

	@DELETE
	@Path("/{cartID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removefromCart(@PathParam("cartID") long cartID) {
		return Response.status(200).entity(cartService.removeFromCart(cartService.readById(cartID))).build();
	}

}
