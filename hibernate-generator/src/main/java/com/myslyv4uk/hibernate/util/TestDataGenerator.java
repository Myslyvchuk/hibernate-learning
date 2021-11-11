package com.myslyv4uk.hibernate.util;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.myslyv4uk.hibernate.entity.Account;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataGenerator {

    public static List<Account> generateAccountList(int size) {
        return Stream.generate(TestDataGenerator::generateAccount)
            .limit(size)
            .collect(Collectors.toList());
    }


    public static Account generateAccount() {
        Fairy fairy = Fairy.create();
        Person randomPerson = fairy.person();
        return convertPersonToAccount(randomPerson);
    }

    private static Account convertPersonToAccount(Person person) {
        return Account
            .builder()
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .email(person.getEmail())
            .creationTime(LocalDateTime.now())
            .build();
    }
}