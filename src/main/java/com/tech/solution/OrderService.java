package com.tech.solution;

import java.util.ArrayList;
import java.util.List;

import com.tech.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.dao.OrderDaoI;
import com.tech.model.Company;
import com.tech.model.Order;
import com.tech.service.OrderServiceI;

@Service
public class OrderService implements OrderServiceI {
    /*
     * Use the orderDao to query for orders in the system. You can then filter
     * the results here to return the proper value according to the method
     */
    @Autowired
    OrderDaoI orderDao;

    public OrderService() {
    }

    @Override
    public List<Order> getOrdersByStatus(Company company, int status) {
        List<Order> orders = orderDao.findAll(company);

        List<Order> ordersReturn = new ArrayList<>();

        for (Order order: orders) {
            if(order.getOrderStatus() == status){
                ordersReturn.add(order);
            }
        }


        return ordersReturn;
    }

    @Override
    public double getTotalCostOfOrder(Order order) {

        double total = 0;

        for (OrderItem orderItem: order.getOrderItems()){
            total += (orderItem.getQuantityOrdered() * orderItem.getUnitPrice()) + orderItem.getTax();
        }
        return total;
    }

}
