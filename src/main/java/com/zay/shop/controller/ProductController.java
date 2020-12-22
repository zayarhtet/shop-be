package com.zay.shop.controller;

import com.zay.shop.model.Product;
import com.zay.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable(value = "id") Integer id) throws Exception {
        return productService.getProductById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Integer id, @RequestBody Product product) throws Exception {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteProduct(@PathVariable(value = "id") Integer id) throws Exception {
        return productService.deleteProduct(id);
    }
}
