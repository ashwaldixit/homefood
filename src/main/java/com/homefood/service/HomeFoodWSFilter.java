package com.homefood.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.core.TransactionInfo;

@Provider
public class HomeFoodWSFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Autowired
	TransactionInfo transactionInfo;

	private final String AUTHENTICATION_HEADER_KEY = "token";

	@Autowired
	UserAuthenticationTokenService userAuthenticationTokenService;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, Token");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {/*
		if (!getExceptionalRequests().contains(requestContext.getUriInfo().getPath())) {
			if (isExceptionalRequest(requestContext)) {
				return;
			}
			List<String> tokens = requestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
			if (null != tokens && !tokens.isEmpty()) {

				if (!userAuthenticationTokenService.isValidToken(tokens.get(0))) {

					transactionInfo.generateException("unathenticated_request", NotificationInfo.ERROR,
							Status.UNAUTHORIZED.getStatusCode());
				} else {
					return;
				}
			} else {
				transactionInfo.generateException("unathenticated_request", NotificationInfo.ERROR,
						Status.UNAUTHORIZED.getStatusCode());
			}
		} else {
			return;
		}
	*/}

	/**
	 * 
	 * @return the links which don't need token authorization
	 */
	private List<String> getExceptionalRequests() {
		List<String> exceptionalRequests = new ArrayList<String>();
        exceptionalRequests.add("login");
		return exceptionalRequests;
	}

	private boolean isExceptionalRequest(ContainerRequestContext requestContext) {
		if ((requestContext.getUriInfo().getPath().startsWith("infographics")
				&& requestContext.getMethod().equals("GET"))) {

			return true;
		}

		return false;
	}
}
