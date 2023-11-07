package org.comit.pluralisticsecurity.service;

import org.comit.pluralisticsecurity.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
}