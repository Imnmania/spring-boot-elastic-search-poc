package me.niloybiswas.elasticsearch_poc.controller;

import lombok.RequiredArgsConstructor;
import me.niloybiswas.elasticsearch_poc.entity.Product;
import me.niloybiswas.elasticsearch_poc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> findAllProducts() {
        Iterable<Product> allProducts = productService.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }

}
