package com.zay.shop.service;

import com.zay.shop.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();
    ResponseEntity<?> getProductById(Integer id) throws Exception;
    ResponseEntity<?> saveProduct(Product Product);
    ResponseEntity<?> updateProduct(Integer id, Product updatedProduct) throws Exception;
    Map<String,Boolean> deleteProduct(Integer id) throws Exception;
}
