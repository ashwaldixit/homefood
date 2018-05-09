package com.homefood.webservice;

import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.homefood.codetype.CartStatus;
import com.homefood.model.Cart;
import com.homefood.model.Product;
import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.CartService;
import com.homefood.service.ProductService;
import com.homefood.service.UserAuthenticationTokenService;
import com.homefood.service.UserService;

@Path("/cart")
public class CartResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@GET
	@Path("/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveCartDetailsOfUser() throws URISyntaxException {
		return Response.status(200).entity(cartService.readAllActiveByCustomer(getUser())).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertCart(Cart cart) throws URISyntaxException {
		cart.setCustomer(getUser());
		if (cartService.readyByProductAndCustomerAndStatus(cart.getProduct(), cart.getCustomer(),
				CartStatus.ACTIVE) != null) {
			cart.setQuantity(cart.getQuantity() + 1);
			cartService.computeCart(cart.getCustomer());
			return Response.status(200).entity(cartService.update(cart)).build();
		} else {
			return Response.status(200).entity(cartService.createCart(cart)).build();
		}

	}

	@DELETE
	@Path("/{cartID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removefromCart(@PathParam("cartID") long cartID) {
		return Response.status(200).entity(cartService.removeFromCart(cartService.readById(cartID))).build();
	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;
	}

	@GET
	@Path("/compute")
	@Produces(MediaType.APPLICATION_JSON)
	public Response computeActiveCartOfUser() throws URISyntaxException {
		return Response.status(200).entity(cartService.computeCart(getUser())).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Cart cart) {
		return Response.ok().entity(cartService.update(cart)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/product/{productId}")
	public Response getByProduct(@PathParam("productId") long productId) {
		return Response.ok().entity(cartService.readyByProductAndCustomerAndStatus(productService.readById(productId),
				getUser(), CartStatus.ACTIVE)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/product")
	public Response addProductToCart(Product product) {

		return Response.ok()
				.entity(cartService.readyByProductAndCustomerAndStatus(product, getUser(), CartStatus.ACTIVE)).build();
	}

}
