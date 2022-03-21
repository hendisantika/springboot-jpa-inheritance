package com.hendisantika.service;

import com.hendisantika.entity.Product;
import com.hendisantika.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.26
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Storing new product
     *
     * @param product product payload
     * @return stored Product Object
     */
    public Product store(Product product) {
        return productRepository.save(product);
    }

    /**
     * Getting product by id
     *
     * @param id product identifier
     * @return Optional of Product Object
     */
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Getting all products
     *
     * @return List of Product Objects
     */
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
