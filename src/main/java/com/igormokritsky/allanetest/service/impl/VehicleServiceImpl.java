package com.igormokritsky.allanetest.service.impl;

import com.igormokritsky.allanetest.model.vehicles.Vehicle;
import com.igormokritsky.allanetest.repository.VehicleRepository;
import com.igormokritsky.allanetest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
