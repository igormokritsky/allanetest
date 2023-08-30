package com.igormokritsky.allanetest.model.vehicles;

import com.igormokritsky.allanetest.dto.VehicleDTO;
import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int modelYear;

    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private double price;

    @OneToOne(mappedBy = "vehicle")
    private LeasingContract contract;

    public VehicleDTO convertVehicleToDTO() {
        return new ModelMapper().map(this, VehicleDTO.class);
    }
}
