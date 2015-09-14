package org.Rmi.domain;

import java.io.Serializable;

public class UserDO implements Serializable {

	/**
	 * 因为此对象需要现实进行远程传输，所以必须继承Serializable
	 */
	private static final long serialVersionUID = -3877532864989912019L;

	private int id;
	private String name;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
}
