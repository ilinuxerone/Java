package socket.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpServer {

	public static void main(String[] args) {
		/*----------------------
		 * -----迭代服务器---------
		 * ---------------------
		 * 1.创建服务端套接字、绑定服务端口 
		 * 2.创建接收缓冲区，接收客户请求
		 * 3.解析请求
		 * 4.发出响应
		 * 5.关闭套接口
		 * 
		 */
		DatagramSocket dgs = null;
		try {
			dgs = new DatagramSocket(8883);
			byte[] request = new byte[1024];
			byte[] response = new byte[1024];
			while (true) {
				DatagramPacket req = new DatagramPacket(request,
						request.length);
				dgs.receive(req);
				
				response = ConvertUtils.intToByteArray(0);
				DatagramPacket resp = new DatagramPacket(response, response.length, new InetSocketAddress("localhost", 8884));
				//成功返回0,失败返回1
				dgs.send(resp);
			}	
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dgs.close();
			e.printStackTrace();
		} 
	}

	
	
	
}
