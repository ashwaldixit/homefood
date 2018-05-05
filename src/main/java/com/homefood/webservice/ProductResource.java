package com.homefood.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.service.CategoryService;
import com.homefood.service.CatererService;
import com.homefood.service.ProductService;

@Path("/products")
public class ProductResource {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CatererService catererService;

	@GET
	@Path("/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("productid") long productid) {
		return Response.ok().entity(productService.readById(productid)).build();
	}

	@GET
	@Path("/name/active/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByName(@PathParam("name") String name) {
		return Response.ok().entity(productService.readAllActiveByName(name)).build();
	}

	@GET
	@Path("/name/inactive/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInactiveByName(@PathParam("name") String name) {
		return Response.ok().entity(productService.readAllInActiveByName(name)).build();
	}

	@GET
	@Path("/category/active/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByCategory(@PathParam("name") String cat) {
		return Response.ok().entity(productService.readAllActiveByCategory(categoryService.readActiveByName(cat)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> map = uriInfo.getQueryParameters();
		String[] categoryIds = map.get("category").get(0).split(",");
		String[] catererIds = map.get("caterer").get(0).split(",");
		List<Category> categories = new ArrayList<Category>();
		List<Caterer> caterers = new ArrayList<Caterer>();
		 for(String catId :categoryIds ) {
			Category cat = categoryService.readById(Long.parseLong(catId));
			if (null != cat)
				categories.add(cat);
		}

		 for(String catId :catererIds ) {
			Caterer caterer = catererService.readById(Long.parseLong(catId));
			if (null != caterer)
				caterers.add(caterer);
		}

		return Response.ok().entity(productService.findByCategoriesAndCaterers(categories, caterers)).build();

	}

	@GET
	@Path("/category/inactive/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInactiveByCategory(@PathParam("name") String cat) {
		return Response.ok().entity(productService.readAllInActiveByCategory(categoryService.readActiveByName(cat)))
				.build();
	}

	@GET
	@Path("/caterer/active/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByCaterer(@PathParam("name") String cat) {
		return Response.ok().entity(productService.getAllActiveProductsByStatusAndStockAndAvailability(catererService.readActiveByName(cat)))
				.build();
	}

	@GET
	@Path("/caterer/inactive/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInactiveByCaterer(@PathParam("name") String cat) {
		return Response.ok().entity(productService.readAllInActiveByCaterer(catererService.readActiveByName(cat)))
				.build();
	}

	@GET
	@Path("/category/{category}/caterer/{caterer}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByCatererAndCategory(@PathParam("category") String category,
			@PathParam("caterer") String caterer) {
		return Response.ok()
				.entity(productService.readAllActiveByCategoryAndCaterer(categoryService.readActiveByName(category),
						catererService.readActiveByName(caterer)))
				.build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product) {
		return Response.ok().entity(productService.validateAndCreate(product)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) {
		return Response.ok().entity(productService.update(product)).build();
	}

}
