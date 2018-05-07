package com.homefood.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.DayAvailablity;
import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductCreate;
import com.homefood.model.ProductPresence;
import com.homefood.model.ProductPrice;
import com.homefood.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CatererService catererService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TransactionInfo transactionInfo;

	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	ProductPresenceService productPresenceService;

	@Override
	public Product createProduct(Product product) {
		Caterer caterer = catererService.readById(product.getCaterer().getCatererid());
		if (null == caterer)
			caterer = catererService.createCaterer(product.getCaterer());
		product.setCaterer(caterer);

		Category category = categoryService.readById(product.getCategory().getCategoryid());
		if (null == category)
			category = categoryService.createCategory(product.getCategory());
		product.setCategory(category);

		return productRepository.save(product);
	}

	@Override
	public Product readById(long id) {
		return productRepository.findByProductid(id);
	}

	@Override
	public void validate(Product product) {
		List<Object> args = new ArrayList<Object>();
		args.add(product.getClass().getSimpleName() + " name");
		args.add(product.getName());

		List<Product> products = productRepository.findByNameAndCatererAndStatus(product.getName(),
				product.getCaterer(), RecordStatus.Active);
		if (product.getName() == null || product.getName().isEmpty()) {
			transactionInfo.generateException("EMPTY_FILED", args, NotificationInfo.ERROR, 500);
		} else if (!products.isEmpty() || products == null) {
			transactionInfo.generateException("ALREADY_EXISTS", args, NotificationInfo.ERROR, 500);
		}
	}

	@Override
	public List<Product> readAllByNameAndStatus(String name, RecordStatus status) {
		return productRepository.findByNameAndStatus(name, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllActiveByCategory(Category category) {
		return productRepository.findByCategoryAndStatus(category, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCategory(Category category) {
		return productRepository.findByCategoryAndStatus(category, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllActiveByCaterer(Caterer caterer) {
		return productRepository.findByCatererAndStatus(caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCaterer(Caterer caterer) {
		return productRepository.findByCatererAndStatus(caterer, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllByPresenceAndStatus(ProductPresence presence, RecordStatus status) {
		return productRepository.findByPresenceAndStatus(presence, status);
	}

	@Override
	public List<Product> readAllActiveByNameAndCaterer(String name, Caterer caterer) {
		return productRepository.findByNameAndCatererAndStatus(name, caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByNameAndCaterer(String name, Caterer caterer) {
		return productRepository.findByNameAndCatererAndStatus(name, caterer, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllActiveByCategoryAndCaterer(Category category, Caterer caterer) {
		return productRepository.findByCategoryAndCatererAndStatus(category, caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCategoryAndCaterer(Category category, Caterer caterer) {
		return productRepository.findByCategoryAndCatererAndStatus(category, caterer, RecordStatus.InActive);
	}

	@Override
	public Product validateAndCreate(Product product) {
		validate(product);
		return createProduct(product);
	}

	@Override
	public Product update(Product product) {

		if (null != readById(product.getProductid()))
			return createProduct(product);

		transactionInfo.generateException("UPDATE_FAILED", NotificationInfo.ERROR,
				Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return null;

	}

	@Override
	public List<Product> readAllActiveByName(String name) {

		return readAllByNameAndStatus(name, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByName(String name) {
		return readAllByNameAndStatus(name, RecordStatus.InActive);
	}

	@Override
	public List<Product> findByCategoriesAndCaterers(List<Category> categories, List<Caterer> caterers) {
		return productRepository.findByStatusAndCategoryInAndCatererIn(RecordStatus.Active, categories, caterers);
	}

	@Override
	public List<Product> getAllActiveProductsByStatusAndStockAndAvailability(Caterer caterer) {
		return productRepository.getAllActiveProductsByStatusAndStockAndAvailability(caterer, RecordStatus.Active,
				getDayAvailaility(LocalDateTime.now().getDayOfWeek()), LocalDateTime.now());
	}

	private DayAvailablity getDayAvailaility(DayOfWeek dayOfWeek) {
		if (dayOfWeek.getValue() > 5)
			return DayAvailablity.Weekday;
		else
			return DayAvailablity.Weekend;

	}

	@Override
	public Product validateAndcreateProduct(ProductCreate productCreate) {

		Product product = new Product();
		product.setCategory(productCreate.getCategory());
		product.setCaterer(productCreate.getCaterer());
		product.setName(productCreate.getName());
		product.setImageUrl(productCreate.getImageUrl());
		product.setDescription(productCreate.getDescription());
		product = createProduct(product);

		ProductPrice productPrice = new ProductPrice();
		productPrice.setProduct(product);
		productPrice.setPrice(productCreate.getPrice());
		productPrice.setStartDate(LocalDateTime.now());
		productPrice.setEndDate(null);
		productPriceService.createProductPrice(productPrice);

		ProductPresence presence = new ProductPresence();
		presence.setProduct(product);
		presence.setOutofStock(true);
		presence.setStartTime(LocalDateTime.now());
		presence.setEndTime(LocalDateTime.now().plusHours(3));
		productPresenceService.createProductPresence(presence);

		return product;
	}

}
