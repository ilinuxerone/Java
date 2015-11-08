package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.创建服务器套接口，指定端口 
 * 2.调用accept等待客户端连接请求，阻塞 
 * 3.客户端建立连接返回新的套接口
 * 4.使用accpet返回的套接口获取请求数据报文
 * 5.解析请求
 * 6.做出响应 
 * 7.释放资源
 * 
 * @author Administrator
 * 
 */
public class TcpEchoServ {

	public static void main(String[] args) throws IOException {
		ServerSocket socket = null;
		Socket conn_socket = null;
		BufferedReader in = null;
		PrintWriter  out = null;
		String msg = null;
		
		// TODO Auto-generated method stub
		try {
		    socket = new ServerSocket(9999);
			
			while (true) {
				System.out.println("The Server is start:" +socket);
				conn_socket = socket.accept();
				System.out.println("Accept the Client:" + socket);
				
				in = new BufferedReader(new InputStreamReader(conn_socket.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(conn_socket.getOutputStream())), true);
				BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(conn_socket.getOutputStream()));
				
				while (true) {
					String request = in.readLine();
					
					if (null == request)
					{
						break;
					}
					System.out.println("In server received the info:" + request);//打印获得的数据
					out1.write(request);
					out1.newLine();
					out1.flush();
					//out.println(request);
				}
				
				conn_socket.close();
				in.close();
				out.close();
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			socket.close();
		}
		System.out.println("Server is exit");
	}

}
