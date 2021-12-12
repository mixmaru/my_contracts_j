package com.mixmaru.my_contracts_j.repository;

import com.mixmaru.my_contracts_j.domain.entity.IndividualUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualUserRepository extends JpaRepository<IndividualUser, Long> {
}
