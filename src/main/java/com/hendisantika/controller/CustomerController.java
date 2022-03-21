package com.hendisantika.controller;

import com.hendisantika.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
