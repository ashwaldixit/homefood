package com.homefood.webservice;

import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.SupportComment;
import com.homefood.service.SupportCommentService;
import com.homefood.service.SupportTicketService;
import com.homefood.service.UserService;

@Path("{supportTicketID}/supportcomments")
public class SupportCommentResource {

	@Autowired
	SupportCommentService supportCommentService;

	@Autowired
	SupportTicketService supportTicketService;

	@Autowired
	UserService userService;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam("id") long id) {
		return Response.status(Status.OK).entity(supportCommentService.findById(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSupportComment(SupportComment supportComment) throws URISyntaxException {
		return Response.status(201).entity(supportCommentService.save(supportComment)).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSupportComment(SupportComment supportComment) throws URISyntaxException {
		return Response.status(201).entity(supportCommentService.update(supportComment)).build();
	}

	@GET
	@Path("{userID}/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByUserID(@PathParam("userID") long userID) {
		return Response.status(Status.OK)
				.entity(supportCommentService.findByUserAndStatus(userService.readById(userID), RecordStatus.Active))
				.build();
	}

	@GET
	@Path("{userID}/inactive")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInActiveByUserID(@PathParam("userID") long userID) {
		return Response.status(Status.OK)
				.entity(supportCommentService.findByUserAndStatus(userService.readById(userID), RecordStatus.InActive))
				.build();
	}

	@GET
	@Path("/supportticket/{supportticket}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBySupportTicket(@PathParam("supportticket") long supportticket) {
		return Response.status(Status.OK)
				.entity(supportCommentService
						.findBySupportTicketOrderByCreatedDateAsc(supportTicketService.findById(supportticket)))
				.build();
	}

}
