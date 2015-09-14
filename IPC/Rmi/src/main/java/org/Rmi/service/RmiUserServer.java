package org.Rmi.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiUserServer {

	public static void main(String[] args) {
		try {
			UserService personService=new UserServiceImpl();
			//注册通信端口
			LocateRegistry.createRegistry(8888);
			//注册通信路径
			Naming.rebind("rmi://127.0.0.1:8888/PersonService", personService);
			System.out.println("server is ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
