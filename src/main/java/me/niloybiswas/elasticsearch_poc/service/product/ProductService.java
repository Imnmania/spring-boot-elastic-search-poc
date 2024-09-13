package me.niloybiswas.elasticsearch_poc.service.product;

import me.niloybiswas.elasticsearch_poc.dto.ProductDTO;
import me.niloybiswas.elasticsearch_poc.exception.NotFoundException;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> findAllProducts();

    public ProductDTO findProductById(String id) throws NotFoundException;

    public ProductDTO insertProduct(ProductDTO productDTO);

    public ProductDTO updateProduct(ProductDTO productDTO, String id) throws NotFoundException;

    public boolean deleteProduct(String id) throws NotFoundException;
}
