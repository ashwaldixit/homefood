package com.homefood;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.homefood.exception.GenericExceptionMapper;
import com.homefood.exception.HomeFoodExceptionMapper;
import com.homefood.webservice.CategoryResource;
import com.homefood.webservice.CatererResource;
import com.homefood.webservice.CustomerResource;
import com.homefood.webservice.DemoData;
import com.homefood.webservice.OrderResource;
import com.homefood.webservice.ProductPresenceResource;
import com.homefood.webservice.ProductResource;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndPoints();
	}

	public void registerEndPoints() {
		register(DemoData.class);
		register(CatererResource.class);
		register(CategoryResource.class);
		register(ProductResource.class);
		register(ProductPresenceResource.class);
		register(CustomerResource.class);
		register(OrderResource.class);
		register(GenericExceptionMapper.class);
		register(HomeFoodExceptionMapper.class);
	}

}
