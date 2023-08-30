package com.igormokritsky.allanetest.dto;

import com.igormokritsky.allanetest.model.user.Customer;
import java.time.LocalDateTime;
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
public class CustomerDTO extends RepresentationModel<CustomerDTO> {

    @Getter
    private Long id;

    @Getter
    @NotNull(message = "Name cannot be null.")
    @Length(min=3, max=255, message="Name must contain between 3 and 255 characters.")
    private String firstName;

    @Getter
    @NotNull(message = "Last name cannot be null.")
    @Length(min=3, max=255, message="Last name must contain between 3 and 255 characters.")
    private String lastName;

    @Getter
    @NotNull(message = "Birthdate cannot be null.")
    private LocalDateTime birthdate;


    public Customer convertDTOToEntity() {
        return new ModelMapper().map(this, Customer.class);
    }
}
