package jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/17.
 */

@Entity
@Table(name="JPA_CUSTOMERS")
public class Customer {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name="LAST_NAME",length=50,nullable=false)
    private String lastName;

    private String email;

    private int age;

    //映射单向1-N的关联关系
    //使用JoinColumn来映射外键列的名称


    //映射单向 1-n 的关联关系
    //使用 @OneToMany 来映射 1-n 的关联关系
    //使用 @JoinColumn 来映射外键列的名称
    //可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
    //可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
    //注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. '
    //@JoinColumn(name="CUSTOMER_ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,mappedBy="customer")
    private Set<Order> orders = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Temporal(TemporalType.DATE)
    private Date birth;

    public Customer() {
        // TODO Auto-generated constructor stub
    }

    public Customer(String lastName, int age) {
        super();
        this.lastName = lastName;
        this.age = age;
    }
    	/*@TableGenerator(name="ID_GENERATOR",
			table="jpa_id_generators",
			pkColumnName="PK_NAME",
			pkColumnValue="CUSTOMER_ID",
			valueColumnName="PK_VALUE",
			allocationSize=100)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="ID_GENERATOR")*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
