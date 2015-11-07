package socket.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConvertUtils {
	public static  byte[] intToByteArray(int input) throws IOException
	{
		//System.out.println(String.valueOf((input)));
		byte[] result = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(input);
		dos.flush();
		
		result = bos.toByteArray();
		return result;
	}
	
	public static int byteArrayToInt(byte[] input) throws IOException
	{
		int result = 0;
		
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		DataInputStream dis = new DataInputStream(bis);
		result = dis.readInt();
		
		
		return result;
	}
}
