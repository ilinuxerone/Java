package org.IPCClient;

import java.rmi.Naming;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.Rmi.domain.UserDO;
import org.Rmi.service.UserService;

public class RmiUserClient{

	public static void main(String[] args) {
		// 调用远程对象，注意RMI路径与接口必须与服务器配置一致
		try {
			//1.RMI方法 
			UserService personService = (UserService) Naming.lookup("rmi://127.0.0.1:8888/PersonServiceDemo");
			//
			String url = "rmi://127.0.0.1:8888/PersonServiceDemo";
			//2.JNDI方式
			//Context context = new InitialContext();
			//personService = (UserService)context.lookup(url);
			List<UserDO> personList = personService.GetList();
			for (UserDO person : personList) {
				System.out.println("ID:" + person.getId() + " Age:"
						+ person.getAge() + " Name:" + person.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
