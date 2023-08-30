package com.igormokritsky.allanetest.dto;

import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import javax.validation.constraints.DecimalMin;
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
public class LeasingContractDTO extends RepresentationModel<LeasingContractDTO> {

    @Getter
    private Long id;

    @Getter
    @NotNull(message = "Name cannot be null.")
    @Length(min=7, max=7, message="ContractNumber must contain 7 characters.")
    private String contractNumber;

    @DecimalMin("1.0")
    private double monthlyRate;

    private CustomerDTO customer;
    private VehicleDTO vehicle;

    public LeasingContract convertDTOToEntity() {
        return new ModelMapper().map(this, LeasingContract.class);
    }
}
