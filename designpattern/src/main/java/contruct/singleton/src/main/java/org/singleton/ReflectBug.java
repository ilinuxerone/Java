package contruct.singleton.src.main.java.org.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//懒汉式，(如何防止反反射和反序列化漏洞)
public class ReflectBug  implements Serializable{
	private static ReflectBug instance; 
	
	private ReflectBug()
	{//这里可以防止发射漏洞
		if (instance != null)
			throw  new RuntimeException();
	}
	
	public static  synchronized ReflectBug getInstance()
	{
		if (null == instance)
		{
			instance = new ReflectBug();
		}
		
		return instance;
	}
	
	//防止反序列化构造多个对象
	private Object readResolve() throws ObjectStreamException
	{
		return instance;
		
	}

}
