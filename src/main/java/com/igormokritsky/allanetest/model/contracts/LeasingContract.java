package com.igormokritsky.allanetest.model.contracts;

import com.igormokritsky.allanetest.dto.ContractOverviewDTO;
import com.igormokritsky.allanetest.dto.LeasingContractDTO;
import com.igormokritsky.allanetest.model.user.Customer;
import com.igormokritsky.allanetest.model.vehicles.Vehicle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Entity
@Data
public class LeasingContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractNumber;

    @Column(nullable = false)
    private double monthlyRate;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Vehicle vehicle;

    public LeasingContractDTO convertLeasingContractToDTO() {
        return new ModelMapper().map(this, LeasingContractDTO.class);
    }

    public ContractOverviewDTO convertLeasingContractToOverviewDTO() {
        ContractOverviewDTO overviewDTO = new ContractOverviewDTO();
        overviewDTO.setContractNumber(this.getContractNumber());
        overviewDTO.setCustomerSummary(this.getCustomer().getFirstName() + " " + this.getCustomer().getLastName());
        overviewDTO.setVehicleSummary(this.getVehicle().getBrand() + " " + this.getVehicle().getModel() + " (" + this.getVehicle().getModelYear() + ")");
        overviewDTO.setVin(this.getVehicle().getVin() != null ? this.getVehicle().getVin() : "-");
        overviewDTO.setMonthlyRate(this.getMonthlyRate());
        overviewDTO.setVehiclePrice(this.getVehicle().getPrice());
        overviewDTO.setContractDetailsLink(this.getId());
        return overviewDTO;
    }
}
