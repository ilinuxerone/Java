package contruct.singleton.src.main.java.org.singleton;

/**
 * 单例模式：保证一个类只有一个实例，并且提供一个访问该实例的全局访问入口。 
 * 优点： 由于单例模式只生成一个实例，减少系统开销。 应用场景：
 * 1.在servlet编程中，每个servlet是单例，application也是单例。 
 * 2.在struts1框架中，控制器是单例
 * 3.在Spring中，每个bean默认是单例 
 * 4.数据库连接池也设计为单例 
 * 5.读取配置文件的类一般也是单例。
 * 6.应用程序日志一般也是单例，由于共享的日志文件一直处于打开状态，只能有一个实例去操作，否则内容不好追加 
 * 7.网站的计数器也是单例，否则难以同步。
 * 实现方式： 懒汉式、饿汉式、双重检测锁式、静态内部类式
 * 
 */
/*
 * 线程安全、调用效率高、不能延迟加载
 */
public class HungerySingleton {
	private static HungerySingleton singleton = new HungerySingleton();

	private HungerySingleton() {

	}

	public static HungerySingleton getInstance() {
		return singleton;
	}
}
