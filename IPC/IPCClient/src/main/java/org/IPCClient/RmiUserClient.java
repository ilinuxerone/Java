package org.IPCClient;

import java.rmi.Naming;
import java.util.List;

import org.Rmi.domain.UserDO;
import org.Rmi.service.UserService;

public class RmiUserClient{

	public static void main(String[] args) {
		// 调用远程对象，注意RMI路径与接口必须与服务器配置一致
		try {
			UserService personService = (UserService) Naming.lookup("rmi://127.0.0.1:8888/PersonService");
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
