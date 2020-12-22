package com.zay.shop.controller;

import com.zay.shop.model.Order;
import com.zay.shop.model.Order;
import com.zay.shop.payload.OrderRequest;
import com.zay.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrder(@PathVariable(value = "id") Integer id) throws Exception {
        return orderService.getOrderById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) throws Exception{
        return orderService.saveOrder(orderRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(value = "id") Integer id, @RequestBody OrderRequest orderRequest) throws Exception {
        return orderService.updateOrder(id,orderRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteOrder(@PathVariable(value = "id") Integer id) throws Exception {
        return orderService.deleteOrder(id);
    }
}
