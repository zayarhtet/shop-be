package com.zay.shop.service;

import com.zay.shop.exception.ResourceNotFoundException;
import com.zay.shop.model.Customer;
import com.zay.shop.model.Order;
import com.zay.shop.model.OrderItems;
import com.zay.shop.model.Product;
import com.zay.shop.payload.OrderRequest;
import com.zay.shop.repository.CustomerRepository;
import com.zay.shop.repository.OrderItemsRepository;
import com.zay.shop.repository.OrderRepository;
import com.zay.shop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getOrderById(Integer id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with ID " + id));
        return ResponseEntity.ok().body(order);
    }

    @Override
    public ResponseEntity<?> saveOrder(OrderRequest orderRequest) throws Exception {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with ID " + orderRequest.getCustomerId()));

        Order order = new Order(orderRequest.getOrderNo(),orderRequest.getStatus(),customer);
        Order savedOrder = orderRepository.saveAndFlush(order);

        for(Integer productId: orderRequest.getProductId()) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with ID " + orderRequest.getProductId()));

            final double TOTAL = (double) (product.getPrice() * orderRequest.getQuantity());
            OrderItems orderItems = new OrderItems(orderRequest.getQuantity(), TOTAL, savedOrder, product);
            orderItemsRepository.save(orderItems);
        }
        return ResponseEntity.ok().body(savedOrder);
    }

    @Override
    public ResponseEntity<?> updateOrder(Integer id, OrderRequest orderRequest) throws Exception {
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with ID " + id));

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with ID " + orderRequest.getCustomerId()));

        Order order = new Order(orderRequest.getOrderNo(),orderRequest.getStatus(),customer);
        BeanUtils.copyProperties(order ,oldOrder ,"id","orderDate","orderItems");

        return ResponseEntity.ok().body(orderRepository.saveAndFlush(oldOrder));
    }

    @Override
    public Map<String,Boolean> deleteOrder(Integer id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with ID " + id));
        orderRepository.delete(order);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
