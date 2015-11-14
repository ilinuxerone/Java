package socket.tcp;

import java.io.Closeable;
import java.io.IOException;

public class SocketUtils {
	public static void closeSock(Closeable... sock)
	{
		for (Closeable temp : sock)
		{
			if (null != temp)
			{
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
