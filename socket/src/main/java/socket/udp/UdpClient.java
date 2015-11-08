package socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClient {

	public static void main(String[] args) {

		try {
			DatagramSocket dgs = new DatagramSocket(8884);
			BufferedReader isr = new BufferedReader(new InputStreamReader(
					System.in));
			String input = isr.readLine();
			while ((!input.equalsIgnoreCase("exit"))) {
				// TODO Auto-generated method stub
				byte[] request = input.getBytes();

				DatagramPacket req = new DatagramPacket(request,
						request.length,
						new InetSocketAddress("localhost", 8883));

				dgs.send(req);

				byte[] response = new byte[1024];
				DatagramPacket resp = new DatagramPacket(response,
						response.length);
				dgs.receive(resp);
				System.out.println(ConvertUtils.byteArrayToInt(resp.getData()));
				input = isr.readLine();
			}
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
