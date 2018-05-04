package com.homefood.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.ProductPrice;
import com.homefood.service.ProductPriceService;

@Path("/productprices")
public class ProductPriceResource {

	@Autowired
	ProductPriceService productPriceService;

	@GET
	@Path("/{productPriceid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("productPriceid") long productPriceid) {
		return Response.ok().entity(productPriceService.readById(productPriceid)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProductPrice(ProductPrice productPrice) {
		return Response.ok().entity(productPriceService.createProductPrice(productPrice)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProductPrice(ProductPrice productPrice) {
		return Response.ok().entity(productPriceService.update(productPrice)).build();
	}
}
