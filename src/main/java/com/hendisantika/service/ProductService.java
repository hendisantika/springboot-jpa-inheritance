package com.hendisantika.service;

import com.hendisantika.entity.Product;
import com.hendisantika.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
