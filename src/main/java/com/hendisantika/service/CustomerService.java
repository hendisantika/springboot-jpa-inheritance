package com.hendisantika.service;

import com.hendisantika.entity.Customer;
import com.hendisantika.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jpa-inheritance
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/03/22
 * Time: 21.25
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Storing new customer
     *
     * @param customer customer payload
     * @return stored Customer Object
     */
    public Customer store(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Get customer by id
     *
     * @param id customer identifier
     * @return Optional of Customer Object
     */
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        return customerRepository.findById(id);
    }
}
