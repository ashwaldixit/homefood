package com.homefood.webservice;

import java.time.LocalDateTime;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;
import com.homefood.repository.CategoryRepository;
import com.homefood.repository.CatererRepository;
import com.homefood.repository.ProductPresenceRepository;
import com.homefood.repository.ProductRepository;

@Path("/demodata")
@Component
public class DemoData {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CatererRepository catererRepository;

	@Autowired
	ProductPresenceRepository productPresenceRepository;

	@POST
	public void generateDemoData() {

		Caterer caterer = new Caterer();
		caterer.setName("Sumi");
		caterer.setDescription("First Caterer");

		caterer = catererRepository.save(caterer);

		Category category = new Category();
		category.setName("South Indian");
		category = categoryRepository.save(category);

		Product product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Idli");
		product.setDescription("South Indian Breakfast");
		product = productRepository.save(product);

		ProductPresence presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceRepository.save(presence);

	}

}
