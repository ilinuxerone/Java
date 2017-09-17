package jpa.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/17.
 */

@Entity
@Table(name="JPA_CUSTOMERS")
public class Customer {

    private Integer id;
    private String lastName;

    private String email;
    private int age;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="LAST_NAME",length=50,nullable=false)
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
}
