package com.igormokritsky.allanetest.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.igormokritsky.allanetest.model.user.Customer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testSave() {
        Customer customer = new Customer(1L, "First Name", "Last Name",
            LocalDateTime.of(1996, 11, 25, 0,0,0), "email@gmail.com", new ArrayList<>());
        Customer response = repository.save(customer);
        assertNotNull(response);
    }

}
