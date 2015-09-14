package org.Rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.Rmi.domain.UserDO;

public interface UserService extends Remote{
	public List<UserDO> GetList() throws RemoteException;
	public String getUserName() throws RemoteException;
	public UserDO getUserInfo() throws RemoteException;
}
