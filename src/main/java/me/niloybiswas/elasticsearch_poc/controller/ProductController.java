package me.niloybiswas.elasticsearch_poc.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.niloybiswas.elasticsearch_poc.dto.GenericResponse;
import me.niloybiswas.elasticsearch_poc.dto.ProductDTO;
import me.niloybiswas.elasticsearch_poc.entity.Product;
import me.niloybiswas.elasticsearch_poc.exception.NotFoundException;
import me.niloybiswas.elasticsearch_poc.service.elastic_search.ElasticSearchService;
import me.niloybiswas.elasticsearch_poc.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ElasticSearchService elasticSearchService;
    private final ModelMapper modelMapper;

    // region REST implementation

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<ProductDTO> allProducts = productService.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.insertProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable(name = "id") String id) throws NotFoundException {
        final ProductDTO foundProduct = productService.findProductById(id);
        return ResponseEntity.ok(foundProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable(name = "id") String id) throws NotFoundException {
        final ProductDTO updatedProduct = productService.updateProduct(productDTO, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteProductById(@PathVariable(name = "id") String id) throws NotFoundException {
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

    // endregion

    // region DSL Implementation

    @GetMapping("/matchAll")
    public ResponseEntity<?> matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllService();
        return ResponseEntity.ok(searchResponse.hits().hits().toString());
    }

    @GetMapping("/matchAllProducts")
    public ResponseEntity<List<ProductDTO>> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProduct();
        return ResponseEntity.ok(extractProductDTOSFromSearchResponse(searchResponse));
    }

    @GetMapping("/matchProductsWithName/{name}")
    public ResponseEntity<List<ProductDTO>> matchAllProducts(@PathVariable String name) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchProductWithName(name);
        return ResponseEntity.ok(extractProductDTOSFromSearchResponse(searchResponse));
    }

    private List<ProductDTO> extractProductDTOSFromSearchResponse(SearchResponse<Product> searchResponse) {
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            final Product product = hit.source();
            final ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    // endregion
}
