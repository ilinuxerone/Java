package jpa.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/18.
 */
@Table(name="JPA_ORDERS")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="ORDER_NAME")
    private String orderName;

    //映射单向 n-1 的关联关系
    //使用 @ManyToOne 来映射多对一的关联关系
    //使用 @JoinColumn 来映射外键.
    //可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
    @JoinColumn(name="CUSTOMER_ID")
    @ManyToOne(fetch=FetchType.LAZY)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Order() {
    }
}
