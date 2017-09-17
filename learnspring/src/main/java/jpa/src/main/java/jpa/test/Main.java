package jpa.test;

import jpa.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/17.
 */
public class Main {
    public static void main(String[] args){
        //1. 创建EntitymanagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");

        //2. 创建EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //3. 开启事务
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //4. 进行持久化操作
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setEmail("testemail");
        customer.setLastName("zzh");
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        entityManager.persist(customer);

        //5. 提交事务
        entityTransaction.commit();

        //6. 关闭EntityManager
        entityManager.close();

        //7. 关闭EntityManagerFactory
        entityManagerFactory.close();
    }
}
