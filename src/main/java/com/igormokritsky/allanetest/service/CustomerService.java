package com.igormokritsky.allanetest.service;

import com.igormokritsky.allanetest.model.user.Customer;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);
}
