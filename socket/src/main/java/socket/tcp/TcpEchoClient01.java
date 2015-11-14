package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 1.创建客户端套接口，指定服务器的端口 
 * 2.准备发送数据请求 
 * 3.接收响应
 * 4.关闭套接口
 * 
 * @author Administrator
 * 
 */
public class TcpEchoClient01 {

	public static void main(String[] args) {
		String request = null;
		String response = null;
		BufferedReader in = null;
		PrintWriter   out = null;
		BufferedReader input = null;
		// TODO Auto-generated method stub
		try {
			Socket sock = new Socket("localhost", 9999);
			
			input = new BufferedReader(new InputStreamReader(
					System.in));
			in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					sock.getOutputStream())), true);
			BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(
					sock.getOutputStream()));
			System.out.println("connect to echo Server");
			while (true) {
				request = input.readLine();
				if (request.startsWith("exit"))
				{
					break;
				}
				
				out1.write(request);
				out1.newLine();
				out1.flush();
				//out.println(request);
				
				response = in.readLine();
				
				System.out.println("resonse: " + response);
			}
			sock.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
