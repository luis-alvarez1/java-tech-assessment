package com.tech.solution;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dao.CustomerDaoI;
import com.tech.dao.OrderDaoI;
import com.tech.model.Company;
import com.tech.model.Order;
import com.tech.service.CustomerServiceI;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CustomerService implements CustomerServiceI {

	/*
	 * Use one or both DAO objects here to query for customer and order data,
	 * and to return the lists in the format specified by the method. You may
	 * only need one. Review the documentation and Model to decide
	 */
	@Autowired OrderDaoI orderDao;
	
	@Autowired CustomerDaoI customerDao;
	
	public CustomerService() {
	}

	@Override
	public Map<Integer, List<Order>> getOrdersForCustomer(Company company) {
		return orderDao.findAll(company).stream().collect(groupingBy(Order::getCustomerId));
	}

}
