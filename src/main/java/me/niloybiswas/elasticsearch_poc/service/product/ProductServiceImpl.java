package me.niloybiswas.elasticsearch_poc.service.product;

import lombok.RequiredArgsConstructor;
import me.niloybiswas.elasticsearch_poc.dto.ProductDTO;
import me.niloybiswas.elasticsearch_poc.entity.Product;
import me.niloybiswas.elasticsearch_poc.exception.NotFoundException;
import me.niloybiswas.elasticsearch_poc.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;

    @Override
    public List<ProductDTO> findAllProducts() {
        final Iterable<Product> productList = productRepo.findAll();
        final List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public ProductDTO findProductById(String id) throws NotFoundException {
        final Product product = productRepo.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO insertProduct(ProductDTO productDTO) {
        final Product productToInsert = modelMapper.map(productDTO, Product.class);
        final Product productToReturn = productRepo.save(productToInsert);
        return modelMapper.map(productToReturn, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, String id) throws NotFoundException {
        final Product productToUpdate = productRepo.findById(id).orElseThrow(NotFoundException::new);
        productToUpdate.setName(productDTO.getName());
        productToUpdate.setPrice(productDTO.getPrice());
        productToUpdate.setDescription(productDTO.getDescription());
        productToUpdate.setQuantity(productDTO.getQuantity());
        final Product productToReturn = productRepo.save(productToUpdate);
        return modelMapper.map(productToReturn, ProductDTO.class);
    }

    @Override
    public boolean deleteProduct(String id) throws NotFoundException {
        productRepo.findById(id).orElseThrow(NotFoundException::new);
        productRepo.deleteById(id);
        return true;
    }
}
