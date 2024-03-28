package com.example.order_service.service;

import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.valueObject.Customer;
import com.example.order_service.valueObject.Employee;
import com.example.order_service.valueObject.Product;
import com.example.order_service.valueObject.ResponseValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    public Order findOrderById(String orderId)
    {
        return orderRepository.findOrderById(orderId);
    }
    public ResponseValueObject getUserWithDepartment(String orderId) {
        try {
            ResponseValueObject ResponseValueObject = new ResponseValueObject();
            Order order = orderRepository.findOrderById(orderId);
            Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + order.getProductId(), Product.class);
            Customer customer = restTemplate.getForObject("http://CUSTOMER-SERVICE/customers/" + order.getCustomerId(), Customer.class);
            Employee employee = restTemplate.getForObject("http://EMPLOYEE-SERVICE/employees/" + order.getEmployeeId(), Employee.class);

            ResponseValueObject.setCustomer(customer);
            ResponseValueObject.setProduct(product);
            ResponseValueObject.setEmployee(employee);
            ResponseValueObject.setOrder(order);
            return ResponseValueObject;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}