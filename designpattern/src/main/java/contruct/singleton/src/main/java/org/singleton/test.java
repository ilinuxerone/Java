package contruct.singleton.src.main.java.org.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, Exception, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		//通过反射的方式直接调用私有化构造函数
		Class<ReflectBug> cls = (Class<ReflectBug>)Class.forName("org.singleton.ReflectBug");
		Constructor<ReflectBug> cns = cls.getDeclaredConstructor(null);
		//使私有方法可以访问
		cns.setAccessible(true);
		ReflectBug r1 = cns.newInstance();
		ReflectBug r2 = cns.newInstance();
		
		//通过反序列化方式构造多个对象
		
		
		
	}

}
