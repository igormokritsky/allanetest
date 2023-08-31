package com.igormokritsky.allanetest.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.igormokritsky.allanetest.model.user.Customer;
import com.igormokritsky.allanetest.repository.CustomerRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class CustomerServiceTest {

    static final String EMAIL = "email@gmail.com";

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;

    @Test
    @Order(1)
    public void testSave() {
        BDDMockito.given(repository.save(Mockito.any(Customer.class)))
            .willReturn(getMockCustomer());
        Customer response = service.save(new Customer());

        assertNotNull(response);
    }

    @Test
    @Order(2)
    public void testFindByEmail(){
        BDDMockito.given(repository.findByEmail(Mockito.anyString()))
            .willReturn(Optional.of(getMockCustomer()));

        Optional<Customer> response = service.findByEmail(EMAIL);
        assertFalse(response.isEmpty());
    }

    private Customer getMockCustomer() {
        return new Customer(1L, "First Name", "Last Name",
            LocalDateTime.of(1996, 11, 25, 0,0,0), "email@gmail.com", new ArrayList<>());
    }
}
