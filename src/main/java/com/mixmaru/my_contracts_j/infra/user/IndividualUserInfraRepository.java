package com.mixmaru.my_contracts_j.infra.individual_user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualUserInfraRepository extends JpaRepository<IndividualUserMapper, Long> {
}
