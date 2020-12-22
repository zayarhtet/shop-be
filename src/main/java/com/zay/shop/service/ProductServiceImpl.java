package com.zay.shop.service;

import com.zay.shop.exception.ResourceNotFoundException;
import com.zay.shop.model.Product;
import com.zay.shop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with ID " + id));
        return ResponseEntity.ok().body(product);
    }

    @Override
    public ResponseEntity<?> saveProduct(Product product) {
        return ResponseEntity.ok().body(productRepository.saveAndFlush(product));
    }

    @Override
    public ResponseEntity<?> updateProduct(Integer id, Product updatedProduct) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with ID " + id));
        BeanUtils.copyProperties(updatedProduct,product,"id");
        return ResponseEntity.ok().body(productRepository.saveAndFlush(product));
    }

    @Override
    public Map<String,Boolean> deleteProduct(Integer id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with ID " + id));
        productRepository.delete(product);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
