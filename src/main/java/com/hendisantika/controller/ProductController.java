package com.hendisantika.controller;

import com.hendisantika.entity.Product;
import com.hendisantika.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.29
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Storing new product | POST: /api/product
     *
     * @param product product payload
     * @return ResponseEntity with Product as response body and http status code 201 | 400
     */
    @PostMapping
    public ResponseEntity<Product> store(@Valid @RequestBody Product product) {
        if (product.getId() != null) {
            return ResponseEntity.badRequest().body(product);
        }
        Product storedProduct = productService.store(product);
        return new ResponseEntity<>(storedProduct, HttpStatus.CREATED);
    }
}
