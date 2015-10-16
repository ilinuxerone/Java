package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {

	public static void main(String[] args) {
		/*
		 * 
		 * 1.创建服务端套接字、绑定服务端口 
		 * 2.创建接收缓冲区，接收客户请求
		 * 3.解析请求
		 * 4发出响应
		 * 5.关闭套接口
		 * 
		 */
		try {
			DatagramSocket dgs = new DatagramSocket(8888);
			byte[] response = new byte[1024];
			DatagramPacket dgp = new DatagramPacket(response, response.length);
			dgs.receive(dgp);
			//System.out.println("datagrampacket: " + new String(dgp.getData()));
			dgs.send(dgp);
			dgs.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}
