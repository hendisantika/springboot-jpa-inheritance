package com.hendisantika;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.controller.CustomerController;
import com.hendisantika.entity.Customer;
import com.hendisantika.repository.CustomerRepository;
import com.hendisantika.service.CustomerService;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    @Transactional
    public void storeCustomerTest() throws Exception {
        Customer customer = createCustomer();
        MvcResult mvcResult = mockMvc
                .perform(post(API)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsBytes(customer))
                )
                .andExpect(status().isCreated())
                .andReturn();

        Customer storedCustomer = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), Customer.class
        );
        assertThat(storedCustomer.getValidFrom()).isEqualTo(customer.getValidFrom());
        assertThat(storedCustomer.getValidTo()).isEqualTo(customer.getValidTo());
        assertThat(storedCustomer.getSourceIdentifier()).isEqualTo(customer.getSourceIdentifier());
        assertThat(storedCustomer.getActive()).isEqualTo(customer.getActive());
    }
}
