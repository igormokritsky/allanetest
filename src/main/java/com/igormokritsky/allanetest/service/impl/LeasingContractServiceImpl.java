package com.igormokritsky.allanetest.service.impl;

import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import com.igormokritsky.allanetest.repository.LeasingContractRepository;
import com.igormokritsky.allanetest.service.LeasingContractService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeasingContractServiceImpl implements LeasingContractService {

    @Autowired
    LeasingContractRepository leasingContractRepository;

    @Override
    public LeasingContract save(LeasingContract contract) {
        return leasingContractRepository.save(contract);
    }

    @Override
    public List<LeasingContract> findAll() {
        return leasingContractRepository.findAll();
    }

    @Override
    public LeasingContract findById(Long id) {
        return leasingContractRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
