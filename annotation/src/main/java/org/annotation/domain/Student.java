package org.annotation.domain;

import org.annotation.service.DemoField;
import org.annotation.service.DemoTable;

@DemoTable("student")
public class Student {
	@DemoField(columnName = "id", length = 10, type = "int")
	private int id;
	@DemoField(columnName = "sname", length = 10, type = "varchar")
	private String studentName;
	@DemoField(columnName = "age", length = 3, type = "int")
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
