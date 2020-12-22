package com.zay.shop.service;

import com.zay.shop.exception.ResourceNotFoundException;
import com.zay.shop.model.Order;
import com.zay.shop.model.OrderItems;
import com.zay.shop.model.Product;
import com.zay.shop.payload.OrderItemsRequest;
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
public class OrderItemsServiceImpl implements OrderItemsService{
        @Autowired
        private OrderItemsRepository orderItemsRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private OrderRepository orderRepository;

        @Override
        public List<OrderItems> getAllOrderItems() {
            return orderItemsRepository.findAll();
        }

        @Override
        public ResponseEntity<?> getOrderItemsById(Integer id) throws Exception {
            OrderItems OrderItems = orderItemsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("OrderItems does not exist with ID " + id));
            return ResponseEntity.ok().body(OrderItems);
        }

        @Override
        public ResponseEntity<?> saveOrderItems(OrderItemsRequest orderItemsRequest) throws ResourceNotFoundException {
            Product product = productRepository.findById(orderItemsRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("OrderItems does not exist with ID " + orderItemsRequest.getProductId()));
            final Double TOTAL = (orderItemsRequest.getQuantity() * product.getPrice());
            Order order = orderRepository.findById(orderItemsRequest.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with ID " + orderItemsRequest.getOrderId()));
            OrderItems orderItems = new OrderItems(orderItemsRequest.getQuantity(),TOTAL,order,product);
            return ResponseEntity.ok().body(orderItemsRepository.saveAndFlush(orderItems));
        }

        @Override
        public ResponseEntity<?> updateOrderItems(Integer id, OrderItemsRequest updatedOrderItemsRequest) throws Exception {
            Product product = productRepository.findById(updatedOrderItemsRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("OrderItems does not exist with ID " + updatedOrderItemsRequest.getProductId()));
            final Double TOTAL = (updatedOrderItemsRequest.getQuantity() * product.getPrice());
            Order order = orderRepository.findById(updatedOrderItemsRequest.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with ID " + updatedOrderItemsRequest.getOrderId()));
            OrderItems updatedOrderItems = new OrderItems(updatedOrderItemsRequest.getQuantity(),TOTAL,order,product);
            OrderItems orderItems = orderItemsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("OrderItems does not exist with ID " + id));
            BeanUtils.copyProperties(updatedOrderItems,  orderItems,"id");
            return ResponseEntity.ok().body(orderItemsRepository.saveAndFlush(orderItems));
        }

        @Override
        public Map<String,Boolean> deleteOrderItems(Integer id) throws Exception {
            OrderItems OrderItems = orderItemsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("OrderItems does not exist with ID " + id));
            orderItemsRepository.delete(OrderItems);
            Map<String,Boolean> response = new HashMap<>();
            response.put("deleted",Boolean.TRUE);
            return response;
        }
}


