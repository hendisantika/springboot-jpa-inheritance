package com.hendisantika;

import com.hendisantika.controller.CustomerController;
import com.hendisantika.repository.CustomerRepository;
import com.hendisantika.service.CustomerService;
import javafx.application.Application;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.31
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "no-liquibase")
public class CustomerIntTest {

    private static final String API = "/api/customer";

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerController customerController = new CustomerController(customerService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }
}
