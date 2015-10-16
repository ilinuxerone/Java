package org.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.annotation.service.DemoField;
import org.annotation.service.DemoTable;

public class AnnotationDemo {

	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("org.annotation.domain.Student");
			
			//获取类的所有有效注解
			Annotation[]  annotations = clazz.getAnnotations();
			for (Annotation a : annotations)
			{
				System.out.println(a);
			}
			
			//获得类的指定的注解
			DemoTable dt = (DemoTable)clazz.getAnnotation(DemoTable.class);
			System.out.println(dt.value());
			
			//获得类的属性的注解
			Field field = clazz.getDeclaredField("studentName");
			DemoField demoField = field.getAnnotation(DemoField.class);
			System.out.println(demoField.columnName()+"--"+demoField.type()+"--"+demoField.length());
			
			//根据获得的表明、字段的信息、拼出DDL语句，然后使用JDBC执行该sql，在数据库中生成相关的表
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
