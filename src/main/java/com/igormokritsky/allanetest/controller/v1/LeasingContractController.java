package com.igormokritsky.allanetest.controller.v1;

import com.igormokritsky.allanetest.dto.ContractOverviewDTO;
import com.igormokritsky.allanetest.dto.LeasingContractDTO;
import com.igormokritsky.allanetest.dto.response.Response;
import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import com.igormokritsky.allanetest.service.LeasingContractService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leasing-contract")
public class LeasingContractController {

    @Autowired
    private LeasingContractService leasingContractService;

    @PostMapping
    public ResponseEntity<Response<LeasingContractDTO>> create(@Valid @RequestBody LeasingContractDTO leasingContractDTO, BindingResult result) {

        Response<LeasingContractDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        LeasingContract leasingContract = leasingContractService.save(leasingContractDTO.convertDTOToEntity());
        LeasingContractDTO dto = leasingContract.convertLeasingContractToDTO();

        createSelfLink(leasingContract, dto);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<List<ContractOverviewDTO>>> findOverview() {
        Response<List<ContractOverviewDTO>> response = new Response<>();
        List<LeasingContract> contracts = leasingContractService.findAll();
        List<ContractOverviewDTO> collect = contracts.stream().map(LeasingContract::convertLeasingContractToOverviewDTO).collect(Collectors.toList());
        response.setData(collect);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void createSelfLink(LeasingContract leasingContract, LeasingContractDTO leasingContractDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(leasingContract.getId()).withSelfRel();
        leasingContractDTO.add(selfLink);
    }

}
