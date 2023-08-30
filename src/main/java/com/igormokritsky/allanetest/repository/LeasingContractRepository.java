package com.igormokritsky.allanetest.repository;

import com.igormokritsky.allanetest.model.contracts.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {


}
