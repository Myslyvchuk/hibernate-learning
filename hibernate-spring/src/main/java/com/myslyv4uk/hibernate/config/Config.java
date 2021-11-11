package com.myslyv4uk.hibernate.config;

import com.myslyv4uk.hibernate.repository.AccountRepository;
import com.myslyv4uk.hibernate.util.TestDataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Config implements InitializingBean {

    private final AccountRepository accountRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        accountRepository.saveAll(TestDataGenerator.generateAccountList(5));
    }
}
