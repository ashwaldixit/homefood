package com.homefood;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;

import com.homefood.webservice.DemoData;

@Provider
public class JerseyConfig extends ResourceConfig {
	public void register() {
		register(DemoData.class);
	}

}
