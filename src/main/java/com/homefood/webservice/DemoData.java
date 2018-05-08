package com.homefood.webservice;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homefood.codetype.DayAvailablity;
import com.homefood.codetype.UserRole;
import com.homefood.model.Cart;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.CatererLocation;
import com.homefood.model.User;
import com.homefood.model.CustomerOrder;
import com.homefood.model.Product;
import com.homefood.model.ProductOrder;
import com.homefood.model.ProductPresence;
import com.homefood.model.ProductPrice;
import com.homefood.repository.CategoryRepository;
import com.homefood.repository.CatererRepository;
import com.homefood.repository.LocationRepository;
import com.homefood.repository.ProductPresenceRepository;
import com.homefood.repository.ProductRepository;
import com.homefood.service.CartService;
import com.homefood.service.CategoryService;
import com.homefood.service.CatererLocationService;
import com.homefood.service.CatererService;
import com.homefood.service.CustomerOrderService;
import com.homefood.service.LocationService;
import com.homefood.service.UserService;
import com.homefood.service.ProductOrderService;
import com.homefood.service.ProductPresenceService;
import com.homefood.service.ProductPriceService;
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
	UserService customerService;

	@Autowired
	ProductOrderService orderService;

	@Autowired
	CustomerOrderService customerOrderService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	LocationService locationService;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	CatererLocationService catererLocationService;

	@POST
	public void generateDemoData() throws NoSuchAlgorithmException {

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

		ProductPrice productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(27);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		ProductPresence presence = new ProductPresence();
		presence.setProduct(product);
		presence.setOutofStock(true);
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

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(50);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

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

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(30);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Chapathi");
		product.setImageUrl("https://thumbs.dreamstime.com/b/indian-bread-chapathi-hand-made-33805774.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(40);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Upma");
		product.setImageUrl("https://media.hungryforever.com/wp-content/uploads/2017/06/27115919/veg-upma-recipe.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(33);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setOutofStock(true);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Avalakki");
		product.setImageUrl("http://www.sailusfood.com/wp-content/uploads/2013/11/huli-avalakki-breakfast-recipes.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(25);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Poori");
		product.setImageUrl("https://i2.wp.com/honalu.net/wp-content/uploads/2015/05/poori-sagu.jpg?ssl=1");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(40);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setAvailablity(DayAvailablity.Weekend);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Raagi Rotti");
		product.setImageUrl(
				"http://www.archanaskitchen.com//images/archanaskitchen/1-Author/Gauravi_Vinay/Ragi_Ujju_Rotti.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(33);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		product = new Product();
		product.setCategory(category);
		product.setCaterer(caterer);
		product.setName("Rava Idli");
		product.setImageUrl("http://www.secondrecipe.com/wp-content/uploads/2017/01/idli.jpg");
		product.setDescription("South Indian Breakfast");
		product = productService.createProduct(product);

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(25);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

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

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(30);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

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

		productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(75);
		productPrice.setStartDate(LocalDateTime.now().plusDays(-1));
		productPrice.setEndDate(LocalDateTime.now().plusYears(2));
		productPriceService.createProductPrice(productPrice);

		presence = new ProductPresence();
		presence.setProduct(product);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		User customer = new User();
		customer.setUserName("Ashwal");
		customer.setEmail("ashwalappi@gmail.com");
		customer.setPassword("p");
		customer.setConfirmPassword("p");
		customer.setUserRole(UserRole.Customer);
		customer = customerService.validateAndCreateCustomer(customer);

		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.setProduct(product);
		cartService.createCart(cart);

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
		cart = new Cart();
		cart.setCustomer(customer);
		cart.setProduct(product);
		cartService.createCart(cart);

		locationService.loadCitiesFromExcel();

		CatererLocation catererLocation = new CatererLocation();
		catererLocation.setCaterer(caterer);
		catererLocation.setLocation(locationRepository.findAll().get(0));
		catererLocationService.validateAndCreate(catererLocation);

		catererLocation = new CatererLocation();
		catererLocation.setCaterer(caterer);
		catererLocation.setLocation(locationRepository.findAll().get(1));
		catererLocationService.validateAndCreate(catererLocation);

		catererLocation = new CatererLocation();
		catererLocation.setCaterer(caterer);
		catererLocation.setLocation(locationRepository.findAll().get(2));
		catererLocationService.validateAndCreate(catererLocation);

		System.out.println("*****************************Done with Demo Data *************************************");

	}

}
