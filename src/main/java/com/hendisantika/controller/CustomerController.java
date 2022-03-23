package com.hendisantika.controller;

import com.hendisantika.entity.Customer;
import com.hendisantika.service.CustomerService;
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
 * Time: 21.28
 * To change this template use File | Settings | File Templates.
 */
@RestController
@Tag(name = "Customer", description = "Endpoints for managing Customer")
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor dependency injector
     *
     * @param customerService customer service
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Storing new customer | POST: /api/customer
     *
     * @param customer customer payload
     * @return ResponseEntity with Customer as response body and http status code 201 | 400
     */
    @PostMapping
    @Operation(
            summary = "Add New Customer",
            description = "Add New Customer.",
            tags = {"Customer"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Customer.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<Customer> store(@Valid @RequestBody Customer customer) {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().body(customer);
        }
        Customer storedCustomer = customerService.store(customer);
        return new ResponseEntity<>(storedCustomer, HttpStatus.CREATED);
    }

    /**
     * Get customer by unique identifier | GET : /api/customer/{id}
     *
     * @param id customer identifier
     * @return ResponseEntity with Customer as response body and http status code 200
     */
    @GetMapping(value = "/{id}")
    @Operation(
            summary = "Get Customer by ID",
            description = "Get Customer by ID.",
            tags = {"Customer"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Customer.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<Customer> getOneById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findOne(id);
        return customerOptional
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all customers | GET : /api/customer
     *
     * @return ResponseEntity with List of Customer objects as response body and http status code 200
     */
    @GetMapping
    @Operation(
            summary = "List All Customers",
            description = "List All Customers.",
            tags = {"Customer"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            Customer.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.findAll();
        return Optional.of(customers)
                .map(customerList -> new ResponseEntity<>(customerList, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
