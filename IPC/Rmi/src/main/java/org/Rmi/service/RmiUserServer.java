package org.Rmi.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RmiUserServer {

	public static void main(String[] args) {
		try {
			UserService personService=new UserServiceImpl();
			//注册通信端口
			Registry registry = LocateRegistry.createRegistry(8888);
			//注册通信路径
			//1.Naming:完整的URI方式的服务名称 2.Registry 3.JNDI:需要指定完整的URI方式的服务名称
			Naming.rebind("rmi://127.0.0.1:8888/PersonServiceDemo", personService);
			//registry.bind("PersonServiceDemo", personService);
			//Context nameContext = new InitialContext();
			//nameContext.rebind("rmi://127.0.0.1:8888/PersonServiceDemo", personService);
			System.out.println("server is ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
