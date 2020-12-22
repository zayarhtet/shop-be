package com.zay.shop.service;

import com.zay.shop.exception.ResourceNotFoundException;
import com.zay.shop.model.Order;
import com.zay.shop.payload.OrderRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrders();
    ResponseEntity<?> getOrderById(Integer id) throws Exception;
    ResponseEntity<?> saveOrder(OrderRequest orderRequest) throws Exception;
    ResponseEntity<?> updateOrder(Integer id, OrderRequest updatedOrder) throws Exception;
    Map<String,Boolean> deleteOrder(Integer id) throws Exception;
}
