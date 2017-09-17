package jpa.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/15.
 */

@Table(name="user")
@Entity
@NamedQueries( {
        @NamedQuery( name="User.findByUser5", query = "SELECT u FROM User u where u.name = 'zzh'" ),
        @NamedQuery( name="User.classicQuery", query = "SELECT u FROM User u where u.name = :name" )
})
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="age")
    private int age;

    @Column(name="name")
    private String name;

    public User() {
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
