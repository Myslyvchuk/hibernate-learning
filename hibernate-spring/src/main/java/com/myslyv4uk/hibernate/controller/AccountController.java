package com.myslyv4uk.hibernate.controller;

import com.myslyv4uk.hibernate.entity.Account;
import com.myslyv4uk.hibernate.repository.AccountRepository;
import com.myslyv4uk.hibernate.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void removeById(@PathVariable Long id){
        accountService.removeById(id);
    }

}
