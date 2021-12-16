package com.mixmaru.my_contracts_j.infra.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfraRepository extends JpaRepository<UserMapper, Long> {
}
