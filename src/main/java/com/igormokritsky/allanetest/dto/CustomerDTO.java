package com.igormokritsky.allanetest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("birthdate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime birthdate;

    @Getter
    @NotNull(message = "Email cannot be null.")
    @Length(min=10, max=50, message="Email must contain between 10 and 50 characters.")
    private String email;

    public Customer convertDTOToEntity() {
        return new ModelMapper().map(this, Customer.class);
    }
}
