package socket.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server02 {

	
	
	public static void main(String[] args) {
		 ServerSocket socket ;
		 Socket sock;
		// TODO Auto-generated method stub
		try {
			socket = new ServerSocket(9999);
			
			while (true)
			{
				
				sock = socket.accept();
				DataInputStream in = new DataInputStream(sock.getInputStream());
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
				while (true)
				{
					String msg = in.readUTF();
/*					if (null == msg || "".equalsIgnoreCase(msg))
					{
						break;
					}
					
*/					System.out.println(msg);
					out.writeUTF(msg);
					out.flush();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
