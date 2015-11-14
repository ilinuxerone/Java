package socket.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

public class Sender implements Runnable {
	
	private Reader in;
	private DataOutputStream out;
	boolean isRunning = true;
	
	Sender()
	{
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	Sender(Socket sock)
	{
		this();
		try {
			out = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			isRunning = false;
			SocketUtils.closeSock(in, out);
		}
	}
	
	public String getMsgFromConsol(Reader in)
	{
		String message = "";
		
		try {
			message = ((BufferedReader)in).readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
	
	public void send()
	{
		String msg = getMsgFromConsol(in);
		if (null != msg && !"".equalsIgnoreCase(msg))
		{
			try {
				out.writeUTF(msg);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				isRunning = false;
				SocketUtils.closeSock(in, out);
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning)
		{
			send();
		}
	}

}
