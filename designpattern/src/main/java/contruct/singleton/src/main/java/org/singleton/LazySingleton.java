package contruct.singleton.src.main.java.org.singleton;
/*
 * 现场安全、调用效率不高、能延迟加载
 */
public class LazySingleton {
	private static LazySingleton singleton;

	private LazySingleton() {

	}

	public static synchronized LazySingleton getInstance() {
		if (null == singleton) {
			singleton = new LazySingleton();
		}
		return singleton;
	}
}
