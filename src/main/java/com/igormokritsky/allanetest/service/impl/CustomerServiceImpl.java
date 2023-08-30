package com.igormokritsky.allanetest.service.impl;

import com.igormokritsky.allanetest.model.user.Customer;
import com.igormokritsky.allanetest.repository.CustomerRepository;
import com.igormokritsky.allanetest.service.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
