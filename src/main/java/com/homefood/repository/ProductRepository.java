package com.homefood.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.DayAvailablity;
import com.homefood.codetype.FoodType;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;

@Repository
public interface ProductRepository extends CrudRepository<Product, Serializable> {

	public Product findByProductid(long id);

	public List<Product> findByNameAndStatus(String name, RecordStatus status);

	public List<Product> findByCategoryAndStatus(Category category, RecordStatus status);
	public List<Product> findByCatererAndFoodTypeAndStatus(Caterer caterer, FoodType foodType, RecordStatus status);

	public List<Product> findByCatererAndStatus(Caterer caterer, RecordStatus status);

	public List<Product> findByPresenceAndStatus(ProductPresence presence, RecordStatus status);

	public List<Product> findByNameAndCatererAndStatus(String name, Caterer caterer, RecordStatus status);

	public List<Product> findByCategoryAndCatererAndStatus(Category category, Caterer caterer, RecordStatus status);

	public List<Product> findByStatusAndCategoryInAndCatererIn(RecordStatus status, List<Category> categories,
			List<Caterer> caterers);

	@Query("select p from #{#entityName} p where p.caterer=?1 and status=?2 and productid in(select product from productpresence a where a.outofStock=false  AND (a.startTime < LOCALTIMESTAMP and a.endTime >	 LOCALTIMESTAMP  or  (a.availablity='Everyday' and a.availablity=?3)))")
	public List<Product> getAllActiveProductsByStatusAndStockAndAvailability(Caterer caterer, RecordStatus status,
			DayAvailablity availablity, LocalDateTime dateTime);

	public List<Product> findByStatus(RecordStatus status);

}
