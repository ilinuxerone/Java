package socket.chatroom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

import socket.tcp.SocketUtils;

public class Sender implements Runnable {

	private Reader in;
	private DataOutputStream out;
	boolean isRunning = true;
	private String name;

	Sender() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	Sender(Socket sock) {
		this();
		try {
			out = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			isRunning = false;
			SocketUtils.closeSock(in, out);
		}
	}

	public Sender(Socket sock, String name) {
		// TODO Auto-generated constructor stub
		this(sock);
		this.name = name;
		this.send(name);
	}

	public String getMsgFromConsol(Reader in) {
		String message = "";

		try {
			message = ((BufferedReader) in).readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	public void send(String msg) {
		// String msg = getMsgFromConsol(in);
		if (null != msg && !"".equalsIgnoreCase(msg)) {
			try {
				out.writeUTF(msg);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				isRunning = false;
				SocketUtils.closeSock(in, out);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {
			send(getMsgFromConsol(in));
		}
	}
}
