package contruct.singleton.src.main.java.org.singleton;

public class DoubleLockCheck {
	private volatile static DoubleLockCheck doubleLock;
	
	private DoubleLockCheck()
	{
		
	}
	
	public static DoubleLockCheck getInstance()
	{
		if (null == doubleLock)
		{
			synchronized (DoubleLockCheck.class) {
				if (null == doubleLock){
					doubleLock = new DoubleLockCheck();
				}
			}
		}
		return doubleLock;
	}
}
