package com.mixmaru.my_contracts_j.infra.user;

import com.mixmaru.my_contracts_j.infra.user.mapper.CorporationUserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationUserInfraRepository extends JpaRepository<CorporationUserMapper, Long> {
}
