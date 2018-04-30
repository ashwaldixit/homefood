package com.homefood.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class HomeFoodRuntimeExceptionMapper implements ExceptionMapper<HomeFoodRuntimeException> {
	private static final Logger logger = LoggerFactory.getLogger(HomeFoodRuntimeExceptionMapper.class);

	@Override
	public Response toResponse(HomeFoodRuntimeException obsAppException) {
		logger.error(obsAppException.fillInStackTrace().toString());
		return Response.status(obsAppException.getExceptionMessage().getErrorCode())
				.entity(obsAppException.getExceptionMessage()).type(MediaType.APPLICATION_JSON).build();
	}
}
