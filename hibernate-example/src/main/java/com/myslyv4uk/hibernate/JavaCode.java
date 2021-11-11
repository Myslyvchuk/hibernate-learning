package com.myslyv4uk.hibernate;

import com.myslyv4uk.hibernate.entity.Account;
import com.myslyv4uk.hibernate.util.TestDataGenerator;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.HibernateException;

public class JavaCode {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateLearning");

    public static void main(String[] args) {

        Account account = getEntity();
        executeInTx(em -> em.persist(account));

        long accountId = account.getId();

        System.out.println(accountId);

        executeInTx(em -> {
            final Account account1 = em.find(Account.class, accountId);
            System.out.println("Found account " + account1);
        });

//        executeInTx(em -> {
//            // won'twork as entity is deatached
//            em.remove(account);
//        });

        executeInTx(em -> {
            final Account managedAccount = em.merge(account);
            em.remove(managedAccount);
        });


//        executeInTx(em -> {
//            final Account managedAccount = em.merge(account);
//            final Account newAccount = managedAccount.toBuilder().id(null).build();
//            em.remove(managedAccount);
//            //won't be executed because of order of execution in Action Queue. First persist will be executed and then delete
//            // which will result into constraint violation
//            em.persist(newAccount);
//        });

        executeInTx(em -> {
            final Account entity = getEntity();
            em.persist(entity);
            final Account managedAccount = em.find(Account.class, entity.getId());
            managedAccount.setFirstName("Bodya");
        });

        emf.close();

    }

    private static Account getEntity() {
        return TestDataGenerator.generateAccount();
    }

    private static void executeInTx(Consumer<EntityManager> consumer){
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new HibernateException(e);
        } finally {
            em.close();
        }
    }

}