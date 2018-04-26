package com.homefood.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.model.Category;
import com.homefood.model.Product;
import com.homefood.repository.CategoryRepository;
import com.homefood.repository.ProductRepository;

@Path("/demodata")
@Component
public class DemoData {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@POST
	public void generateDemoData() {

		List<Category> categories = new ArrayList<Category>();
		Category category = new Category();
		category.setName("Breakfast");
		categories.add(categoryRepository.save(category));
		category = new Category();
		category.setName("Lunch");
		categories.add(categoryRepository.save(category));

		Product product = new Product();
		product.setName("idli");
		product.setDescription("morning breakfast");
		product.setCategories(categories);
		productRepository.save(product);

	}

}
