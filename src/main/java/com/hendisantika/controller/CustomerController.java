package com.hendisantika.controller;

import com.hendisantika.entity.Customer;
import com.hendisantika.service.CustomerService;
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
 * Time: 21.28
 * To change this template use File | Settings | File Templates.
 */
@RestController
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
    public ResponseEntity<Customer> store(@Valid @RequestBody Customer customer) {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().body(customer);
        }
        Customer storedCustomer = customerService.store(customer);
        return new ResponseEntity<>(storedCustomer, HttpStatus.CREATED);
    }
}
