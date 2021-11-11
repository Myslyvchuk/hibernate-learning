package com.myslyv4uk.hibernate.repository;

import com.myslyv4uk.hibernate.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
