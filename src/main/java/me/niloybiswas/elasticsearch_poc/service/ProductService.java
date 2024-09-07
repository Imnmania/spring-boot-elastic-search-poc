package me.niloybiswas.elasticsearch_poc.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.niloybiswas.elasticsearch_poc.dto.ProductDTO;
import me.niloybiswas.elasticsearch_poc.entity.Product;
import me.niloybiswas.elasticsearch_poc.exception.NotFoundException;
import me.niloybiswas.elasticsearch_poc.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;

    public Iterable<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product insertProduct(ProductDTO productDTO) {
        final Product product = modelMapper.map(productDTO, Product.class);
        return productRepo.save(product);
    }

    public Product updateProduct(ProductDTO productDTO, int id) throws NotFoundException {
        final Product productToUpdate = Optional.of(productRepo.findById(id).orElseThrow(NotFoundException::new)).get();
        productToUpdate.setName(productDTO.getName());
        productToUpdate.setPrice(productDTO.getPrice());
        productToUpdate.setDescription(productDTO.getDescription());
        productToUpdate.setQuantity(productDTO.getQuantity());
        return productRepo.save(productToUpdate);
    }

    public boolean deleteProduct(int id) throws NotFoundException {
        productRepo.findById(id).orElseThrow(NotFoundException::new);
        productRepo.deleteById(id);
        return true;
    }

}
