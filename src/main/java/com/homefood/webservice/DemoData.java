package com.homefood.webservice;

import java.time.LocalDateTime;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Customer;
import com.homefood.model.CustomerOrder;
import com.homefood.model.Product;
import com.homefood.model.ProductOrder;
import com.homefood.model.ProductPresence;
import com.homefood.repository.CategoryRepository;
import com.homefood.repository.CatererRepository;
import com.homefood.repository.ProductPresenceRepository;
import com.homefood.repository.ProductRepository;
import com.homefood.service.CategoryService;
import com.homefood.service.CatererService;
import com.homefood.service.CustomerOrderService;
import com.homefood.service.CustomerService;
import com.homefood.service.ProductOrderService;
import com.homefood.service.ProductPresenceService;
import com.homefood.service.ProductService;

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

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@Autowired
	CatererService catererService;

	@Autowired
	ProductPresenceService productPresenceService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductOrderService orderService;

	@Autowired
	CustomerOrderService customerOrderService;

	@POST
	public void generateDemoData() {

		Caterer caterer = new Caterer();
		caterer.setName("Sumi");
		caterer.setDescription("First Caterer");
		caterer = catererService.createCaterer(caterer);

		Category category = new Category();
		category.setName("South Indian");
		category = categoryService.createCategory(category);

		Product product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Idli");
		product.setImageUrl("https://www.ruchiskitchen.com/wp-content/uploads/2016/01/instant-rice-idli-recipe-6.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		ProductPresence presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setImageUrl("http://www.dosacrepecafe.com/images/dosa-2.jpg");
		product.setName("Dosa");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setImageUrl("https://i.ytimg.com/vi/0HL2_8QV5mM/maxresdefault.jpg");
		product.setName("Vada");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		category = new Category();
		category.setName("North Indian");
		category = categoryService.createCategory(category);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Roti");
		product.setImageUrl("http://www.strokeinfoblog.com/wp-content/uploads/2017/12/tandoori-roti.jpg");
		product.setDescription("North Indian Breakfast");
		product = productService.createProduct(product);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);
		
		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Panner Butter Masala");
		product.setImageUrl("https://i.ytimg.com/vi/T5tCiHGjEbk/maxresdefault.jpg");
		product.setDescription("North Indian Breakfast");
		product = productService.createProduct(product);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		Customer customer = new Customer();
		customer.setUserName("Ashwal");
		customer.setEmail("ashwalappi@gmail.com");
		customer.setPassword("p");
		customer.setConfirmPassword("p");
		customer = customerService.validateAndCreateCustomer(customer);

		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCreatedDate(LocalDateTime.now());
		customerOrder.setCustomer(customer);
		customerOrder.setLastModifiedDate(LocalDateTime.now().plusHours(3));
		customerOrder = customerOrderService.validateAndCreate(customerOrder);

		ProductOrder order = new ProductOrder();
		order.setProduct(product);
		order.setDeliverydate(LocalDateTime.now().plusDays(2));
		order.setCustomerOrder(customerOrder);
		orderService.validateAndCreate(order);

		System.out.println("*****************************Done with Demo Data *************************************");

	}

}
