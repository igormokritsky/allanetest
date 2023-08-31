package com.igormokritsky.allanetest.controller.v1;

import com.igormokritsky.allanetest.dto.CustomerDTO;
import com.igormokritsky.allanetest.dto.response.Response;
import com.igormokritsky.allanetest.model.user.Customer;
import com.igormokritsky.allanetest.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ApiOperation(value = "Route to create customer")
    public ResponseEntity<Response<CustomerDTO>> create(@Valid @RequestBody CustomerDTO customerDTO, BindingResult result) {

        Response<CustomerDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Customer customer = customerService.save(customerDTO.convertDTOToEntity());
        CustomerDTO dto = customer.convertCustomerToDTO();

        createSelfLink(customer, dto);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void createSelfLink(Customer customer, CustomerDTO customerDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
        customerDTO.add(selfLink);
    }
}
