package com.igormokritsky.allanetest.controller.v1;

import com.igormokritsky.allanetest.dto.VehicleDTO;
import com.igormokritsky.allanetest.dto.response.Response;
import com.igormokritsky.allanetest.model.vehicles.Vehicle;
import com.igormokritsky.allanetest.service.VehicleService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    public ResponseEntity<Response<VehicleDTO>> create(@Valid @RequestBody VehicleDTO vehicleDTO, BindingResult result) {

        Response<VehicleDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Vehicle vehicle = vehicleService.save(vehicleDTO.convertDTOToEntity());
        VehicleDTO dto = vehicle.convertVehicleToDTO();

        createSelfLink(vehicle, dto);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void createSelfLink(Vehicle vehicle, VehicleDTO vehicleDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(vehicle.getId()).withSelfRel();
        vehicleDTO.add(selfLink);
    }

}
