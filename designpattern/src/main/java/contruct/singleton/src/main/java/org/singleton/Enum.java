package contruct.singleton.src.main.java.org.singleton;
//线程安全，调用效率高，但是不能延迟加载,并且可以防止反射和反序列化漏洞
public enum Enum {
	//这个枚举元素本身就是单例对象。
	INSTANCE;
	
	public void enumOperation()
	{
		
	}
}
