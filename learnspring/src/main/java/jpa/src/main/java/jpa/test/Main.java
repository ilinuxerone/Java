package jpa.test;

import jpa.domain.Customer;
import jpa.domain.Order;

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
       // standardJPAUsage();
        testOneToManyPersist();
    }

    public static void  standardJPAUsage(){
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

    /**
     * 单向1-n关联关系执行保存时，一定会多出update语句，
     * 因为n的一端在插入时不会同时插入外键列
     */
    public static void  testOneToManyPersist(){
        //1. 创建EntitymanagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");

        //2. 创建EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3. 开启事务
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("gg@163.com");
        customer.setLastName("GG");

        Order order1 = new Order();
        order1.setOrderName("G-GG-1");

        Order order2 = new Order();
        order2.setOrderName("G-GG-2");

        //设置关联关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);


        //执行保存操作
        entityManager.persist(order1);
        entityManager.persist(order2);

        entityManager.persist(customer);

        //5. 提交事务
        entityTransaction.commit();

        //6. 关闭EntityManager
        entityManager.close();

        //7. 关闭EntityManagerFactory
        entityManagerFactory.close();
    }
}
