package com.mixmaru.my_contracts_j.infra.user;

import com.mixmaru.my_contracts_j.infra.user.mapper.IndividualUserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualUserInfraRepository extends JpaRepository<IndividualUserMapper, Long> {
}
