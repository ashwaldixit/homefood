package com.homefood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Cart;
import com.homefood.model.CatererLocation;
import com.homefood.model.CustomerOrder;
import com.homefood.model.CustomerOrderResponse;
import com.homefood.model.ProductOrder;
import com.homefood.model.User;
import com.homefood.repository.CustomerOrderRepository;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	CustomerOrderRepository customerOrderRepository;

	@Autowired
	UserService customerService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductOrderService productOrderService;

	@Autowired
	CatererService catererService;

	@Autowired
	CatererLocationService catererLocationService;

	@Autowired
	private TransactionInfo transactionInfo;

	@Override
	public CustomerOrder readById(long id) {
		return customerOrderRepository.findByCustomerorderid(id);
	}

	@Override
	public CustomerOrder validateAndCreate(CustomerOrder customerOrder) {
		validate(customerOrder);
		return create(customerOrder);
	}

	@Override
	public void validate(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerOrder create(CustomerOrder customerOrder) {
		User customer = customerService.readById(customerOrder.getCustomer().getUserid());
		if (null == customer) {
			customer = customerService.createCustomer(customerOrder.getCustomer());
		}
		customerOrder.setCustomer(customer);

		final CustomerOrder customerOrder2 = customerOrder;
		List<Cart> carts = cartService.readAllActiveByCustomer(customerOrder.getCustomer());
		String curentZipCode = customerOrder.getCustomer().getAddresses().get(0).getZipCode();
		customerOrder.setCaterer(catererService.readById(carts.get(0).getProduct().getCaterer().getCatererid()));
		List<CatererLocation> catererLocations = catererLocationService
				.readAllActiveByCaterer(catererService.readById(carts.get(0).getProduct().getCaterer().getCatererid()));
		customerOrder = customerOrderRepository.save(customerOrder);
		List<Object> errorMessges = new ArrayList<Object>();
		errorMessges.add(curentZipCode);
		errorMessges.add(catererLocations);
//		if (!catererLocations.contains(curentZipCode)) {
//			transactionInfo.generateRuntimeException("CATERER_INVLAID_LOCATION", errorMessges, NotificationInfo.ERROR,
//					Status.INTERNAL_SERVER_ERROR.getStatusCode());
//		}
		carts.stream().forEach(cart -> {
			ProductOrder productOrder = new ProductOrder();
			productOrder.setCustomerOrder(customerOrder2);
			productOrder.setDeliverydate(LocalDateTime.now().plusHours(3));
			productOrder.setProduct(cart.getProduct());
			productOrder.setOrderedQuantity(cart.getQuantity());
			productOrder.setDeliveredQuantity(cart.getQuantity());
			productOrderService.validateAndCreate(productOrder);
		});
		cartService.processAllByCustomer(customerOrder.getCustomer());
		return customerOrder;

	}

	@Override
	public CustomerOrder update(CustomerOrder customerOrder) {
		return create(customerOrder);
	}

	@Override
	public List<CustomerOrder> readAllByStatus(RecordStatus recordStatus) {
		return customerOrderRepository.findByStatus(recordStatus);
	}

	@Override
	public List<CustomerOrderResponse> readAllByCustomer(User customer) {
		List<CustomerOrderResponse> customerOrderProductOrderList = new ArrayList<CustomerOrderResponse>();
		List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerOrderByCreatedDateDesc(customer);
		customerOrders.stream().forEach(customerOrder -> {
			CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
			customerOrderResponse.setProductOrders(productOrderService.readAllByCustomerOrder(customerOrder));
			customerOrderResponse.setCustomerOrder(customerOrder);
			customerOrderProductOrderList.add(customerOrderResponse);
		});
		return customerOrderProductOrderList;
	}

	@Override
	public List<CustomerOrder> readAllByCustomerAndStatus(User customer, OrderStatus orderStatus) {
		return customerOrderRepository.findByCustomerAndStatus(customer, orderStatus);
	}

	@Override
	public List<CustomerOrder> readAllOpenOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Open);
	}

	@Override
	public List<CustomerOrder> readAllConfirmedOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Confirmed);
	}

	@Override
	public List<CustomerOrder> readAllDeliveredOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Delivered);
	}

	@Override
	public List<CustomerOrder> readAllCancelledOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Cancelled);
	}

}
