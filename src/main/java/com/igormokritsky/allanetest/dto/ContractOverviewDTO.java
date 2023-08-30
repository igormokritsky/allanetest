package com.igormokritsky.allanetest.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContractOverviewDTO {

    @Getter
    @NotNull(message = "Name cannot be null.")
    @Length(min=7, max=7, message="ContractNumber must contain 7 characters.")
    private String contractNumber;

    @Getter
    @NotNull(message = "Customer Summary cannot be null.")
    @Length(min=3, max=255, message="Customer Summary must contain between 3 and 255 characters.")
    private String customerSummary;

    @Getter
    @NotNull(message = "Vehicle Summary cannot be null.")
    @Length(min=3, max=255, message="Vehicle Summary must contain between 3 and 255 characters.")
    private String vehicleSummary;

    @Getter
    @NotNull(message = "Vin cannot be null.")
    @Length(min=3, max=10, message="Vin must contain between 3 and 255 characters.")
    private String vin;

    @DecimalMin("1.0")
    private double monthlyRate;

    @DecimalMin("1.0")
    private double vehiclePrice;

    @NotNull(message = "Link cannot be null.")
    private Long contractDetailsLink;
}
