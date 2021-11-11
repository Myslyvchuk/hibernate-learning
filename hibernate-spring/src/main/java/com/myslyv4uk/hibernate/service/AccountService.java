package com.myslyv4uk.hibernate.service;

import com.myslyv4uk.hibernate.entity.Account;
import com.myslyv4uk.hibernate.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional//scope of session only if open-in-view: false. Else Session starts when web request is received.
    public void removeById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        System.out.println("SELECT==============");
        accountRepository.delete(account);
    }

}
