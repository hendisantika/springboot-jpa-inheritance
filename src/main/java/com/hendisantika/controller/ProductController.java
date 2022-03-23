package com.hendisantika.controller;

import com.hendisantika.entity.Product;
import com.hendisantika.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
@Tag(name = "Product", description = "Endpoints for managing Product")
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
    @Operation(
            summary = "Add New Product",
            description = "Add New Product.",
            tags = {"Product"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Product.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<Product> store(@Valid @RequestBody Product product) {
        if (product.getId() != null) {
            return ResponseEntity.badRequest().body(product);
        }
        Product storedProduct = productService.store(product);
        return new ResponseEntity<>(storedProduct, HttpStatus.CREATED);
    }

    /**
     * Get product by unique identifier | GET : /api/product/{id}
     *
     * @param id customer identifier
     * @return ResponseEntity with Product as response body and http status code 200
     */
    @GetMapping(value = "/{id}")
    @Operation(
            summary = "Get Product by ID",
            description = "Get Product by ID.",
            tags = {"Product"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Product.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<Product> getOneById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findOne(id);
        return productOptional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all products | GET : /api/product
     *
     * @return ResponseEntity with List of Product objects as response body and http status code 200
     */
    @GetMapping
    @Operation(
            summary = "Get All Products",
            description = "Get All Products.",
            tags = {"Product"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Product.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.findAll();
        return Optional.of(products)
                .map(productList -> new ResponseEntity<>(productList, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
