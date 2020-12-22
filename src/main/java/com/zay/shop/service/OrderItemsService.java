package com.zay.shop.service;

import com.zay.shop.model.OrderItems;
import com.zay.shop.payload.OrderItemsRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderItemsService {
    List<OrderItems> getAllOrderItems();
    ResponseEntity<?> getOrderItemsById(Integer id) throws Exception;
    ResponseEntity<?> saveOrderItems(OrderItemsRequest orderItemsRequest) throws Exception;
    ResponseEntity<?> updateOrderItems(Integer id, OrderItemsRequest updatedOrderItemsRequest) throws Exception;
    Map<String,Boolean> deleteOrderItems(Integer id) throws Exception;
}
