package socket.chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import socket.tcp.SocketUtils;

public class Receiver implements Runnable {
	private DataInputStream in;
	boolean isRunning = true;
	Receiver(Socket sock)
	{
		try {
			in = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			isRunning = true;
			SocketUtils.closeSock(in);
		}
	}

	public String receive()
	{
		String msg = "";
		try {
			msg = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			isRunning = false;
			SocketUtils.closeSock(in);
		}
		return msg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning)
		{
			System.out.println("receive: " + receive());
		}
	}

}
