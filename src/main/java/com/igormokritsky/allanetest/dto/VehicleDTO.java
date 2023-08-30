package com.igormokritsky.allanetest.dto;

import com.igormokritsky.allanetest.model.vehicles.Vehicle;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VehicleDTO extends RepresentationModel<VehicleDTO> {

    @Getter
    private Long id;

    @Getter
    @NotNull(message = "Brand name cannot be null.")
    @Length(min=3, max=255, message="Brand name must contain between 3 and 255 characters.")
    private String brand;

    @Getter
    @NotNull(message = "Model name cannot be null.")
    @Length(min=3, max=255, message="Model name must contain between 3 and 255 characters.")
    private String model;

    @Min(1)
    @NotNull(message = "Model year cannot be null.")
    private int modelYear;

    @Getter
    @NotNull(message = "Vin cannot be null.")
    @Length(min=3, max=10, message="Vin must contain between 3 and 255 characters.")
    private String vin;

    @Min(1)
    @NotNull(message = "Price cannot be null.")
    private double price;

    public Vehicle convertDTOToEntity() {
        return new ModelMapper().map(this, Vehicle.class);
    }
}
