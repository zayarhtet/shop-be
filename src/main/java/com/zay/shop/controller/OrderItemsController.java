package com.zay.shop.controller;

import com.zay.shop.model.OrderItems;
import com.zay.shop.payload.OrderItemsRequest;
import com.zay.shop.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/orderitems")
public class OrderItemsController {
    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping
    public List<OrderItems> getAllOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrderItems(@PathVariable(value = "id") Integer id) throws Exception {
        return orderItemsService.getOrderItemsById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createOrderItems(@RequestBody OrderItemsRequest orderItemsRequest) throws Exception {
        return orderItemsService.saveOrderItems(orderItemsRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderItems(@PathVariable(value = "id") Integer id, @RequestBody OrderItemsRequest orderItemsRequest) throws Exception {
        return orderItemsService.updateOrderItems(id,orderItemsRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteOrderItems(@PathVariable(value = "id") Integer id) throws Exception {
        return orderItemsService.deleteOrderItems(id);
    }
}
