package contruct.singleton.src.main.java.org.singleton;
/*
 * 而这种方式是Singleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
 */
public class innerClass {
	private static class innerClassHolder{
		private static final innerClass instance = new innerClass();
	}
	
	private innerClass(){
		
	}
	public static final innerClass getInstance()
	{
		return innerClassHolder.instance;
	}
}
