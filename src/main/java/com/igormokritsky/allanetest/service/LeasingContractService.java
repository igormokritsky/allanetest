package com.igormokritsky.allanetest.service;

import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import java.util.List;

public interface LeasingContractService {

    LeasingContract save(LeasingContract contract);

    List<LeasingContract> findAll();

    LeasingContract findById(Long id);

}
