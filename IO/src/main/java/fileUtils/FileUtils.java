package fileUtils;

import java.io.Closeable;
import java.io.IOException;

public class FileUtils {
	public static void closeAll(Closeable... fds) throws IOException
	{
		for (Closeable fd : fds)
		{
			if (null != fd)
			{
				fd.close();
			}
		}
	}
	
	
	public static <T extends Closeable> void  generalCloseAll(T... fds) throws IOException
	{
		for (Closeable fd : fds)
		{
			if (null != fd)
			{
				fd.close();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
