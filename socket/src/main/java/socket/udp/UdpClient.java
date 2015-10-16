package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] request = "request".getBytes();
		
		try {
			DatagramPacket dgp = new DatagramPacket(request, request.length, new InetSocketAddress("localhost", 8888));
			DatagramSocket dgs = new DatagramSocket(8880);
			dgs.send(dgp);
			
			byte[] response = new byte[1024];
			DatagramPacket rcv_dgp = new DatagramPacket(response, response.length);
			dgs.receive(rcv_dgp);
			System.out.println("datagrampacket: " + new String(rcv_dgp.getData()));
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
