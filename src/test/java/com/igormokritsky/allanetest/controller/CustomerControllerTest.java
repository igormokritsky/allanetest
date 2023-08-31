package com.igormokritsky.allanetest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.igormokritsky.allanetest.controller.v1.CustomerController;
import com.igormokritsky.allanetest.dto.CustomerDTO;
import com.igormokritsky.allanetest.model.user.Customer;
import com.igormokritsky.allanetest.service.CustomerService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class CustomerControllerTest {

    static final String URL = "/api/v1/customers";
    static final Long ID = 1L;
    static final String NAME = "First Name";
    static final String LAST_NAME = "Last Name";
    static final String EMAIL = "email@gmail.com";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService service;

    HttpHeaders headers;

    @BeforeAll
    private void setUp() {
        headers = new HttpHeaders();
        headers.set("X-api-key", "FX001-ZBSY6YSLP");
    }

    @Test
    @Order(1)
    public void testSave() throws Exception {

        BDDMockito.given(service.save(Mockito.any(Customer.class))).willReturn(getMockCustomer());

        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, LAST_NAME, getDate(), EMAIL))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .headers(headers))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.id").value(ID))
            .andExpect(jsonPath("$.data.firstName").value(NAME))
            .andExpect(jsonPath("$.data.lastName").value(LAST_NAME))
            .andExpect(jsonPath("$.data.email").value(EMAIL));
    }


    private Customer getMockCustomer() {
        return new Customer(1L, "First Name", "Last Name",
            getDate(), "email@gmail.com", new ArrayList<>());
    }

    private String getJsonPayload(Long id, String name, String lastName, LocalDateTime localDate, String email)
        throws JsonProcessingException {
        CustomerDTO dto = new CustomerDTO(id, name, lastName, localDate, email);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(dto);
    }

    private LocalDateTime getDate() {
        String str = "1986-04-08T12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(str, formatter);
    }
}
