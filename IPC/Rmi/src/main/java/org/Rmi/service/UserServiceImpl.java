package org.Rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import org.Rmi.domain.UserDO;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

	protected UserServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3983172309906243919L;

	@Override
	public List<UserDO> GetList() throws RemoteException {
		
		List<UserDO> userList = new LinkedList<UserDO>();
		
		UserDO user1 = new UserDO();
		user1.setAge(25);
		user1.setId(0);
		user1.setName("user1");
		userList.add(user1);
		
		UserDO user2=new UserDO();
		user2.setAge(25);
		user2.setId(1);
		user2.setName("Rose");
		userList.add(user2);
		return userList;
	}

	@Override
	public String getUserName() throws RemoteException {
		
		return "test";
	}

	@Override
	public UserDO getUserInfo() throws RemoteException {
		UserDO user = new UserDO();
		user.setAge(10);
		user.setId(10);
		user.setName("testd");
		return user;
	}

}
