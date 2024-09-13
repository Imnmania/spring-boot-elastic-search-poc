package me.niloybiswas.elasticsearch_poc.controller;

import lombok.RequiredArgsConstructor;
import me.niloybiswas.elasticsearch_poc.dto.GenericResponse;
import me.niloybiswas.elasticsearch_poc.dto.ProductDTO;
import me.niloybiswas.elasticsearch_poc.exception.NotFoundException;
import me.niloybiswas.elasticsearch_poc.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAllProducts() {
        List<ProductDTO> allProducts = productService.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.insertProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable(name = "id") String id) throws NotFoundException {
        final ProductDTO foundProduct = productService.findProductById(id);
        return ResponseEntity.ok(foundProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable(name = "id") String id) throws NotFoundException {
        final ProductDTO updatedProduct = productService.updateProduct(productDTO, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") String id) throws NotFoundException {
        final boolean hasDeleted = productService.deleteProduct(id);
        if (!hasDeleted) {
            return ResponseEntity.badRequest()
                    .body(GenericResponse.builder()
                            .status(400)
                            .message("Bad request")
                            .build());
        }
        return ResponseEntity.ok()
                .body(GenericResponse.builder()
                        .status(200)
                        .message("Product deleted")
                        .build());
    }
}
