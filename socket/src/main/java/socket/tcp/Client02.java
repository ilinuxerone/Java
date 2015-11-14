package socket.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket sock = new Socket("localhost", 9999);
			new Thread(new Sender(sock)).start();
			new Thread(new Receiver(sock)).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
