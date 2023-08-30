package com.igormokritsky.allanetest.model.user;

import com.igormokritsky.allanetest.dto.CustomerDTO;
import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    // I've added this field because we need to have a unique param for every user
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<LeasingContract> contracts = new ArrayList<>();

    public CustomerDTO convertCustomerToDTO() {
        return new ModelMapper().map(this, CustomerDTO.class);
    }
}
