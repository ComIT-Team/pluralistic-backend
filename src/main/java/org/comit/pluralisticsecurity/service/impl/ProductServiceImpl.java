package org.comit.pluralisticsecurity.service.impl;

import org.comit.pluralisticsecurity.entity.Product;
import org.comit.pluralisticsecurity.repository.ProductRepository;
import org.comit.pluralisticsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}