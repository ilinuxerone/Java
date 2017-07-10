package src.com.ht.db;

import java.io.IOException;

import src.com.ht.entity.Table;
import src.com.ht.entity.Tables;
import src.com.ht.exception.MyException;
import src.com.util.comm.Tools;
import src.com.utitl.check.Checker;

public class DB {
	 
	public static String readComm(){
		String s ="";
		try {
			char r=0;
			do{
				r=(char)System.in.read();
				if(r==';') break;
				s+=r; 
			} while(r!=';');
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) throws MyException {
		System.out.println("#########��ӭʹ�ü������ݿ�#########");
		while(true) {
	    String h = readComm().trim();
	    if(h.equals("exit")) break;
		Checker ch = new Checker(h);
		
		try {
			 
			if(ch.getComm().charAt(0)=='c'||ch.getComm().charAt(0)=='C') {
				Table tb = ch.checkCreate();
				Tables t= Tools.readTables();
				if(t==null) {
					if(tb!=null) {
					    Tables tbs = new Tables();
					    tbs.getTables().add(tb);
						Tools.writeTables(tbs);
						System.out.println(tb.getName()+":�����ɹ���~");
					}  else {
						
					 }
				} else {
					 if(t.getTables().contains(tb)) {
						 throw new MyException(tb.getName()+":���Ѿ����ڣ�����ʧ��~");
					 }
					 else {
						 t.getTables().add(tb);
					     Tools.writeTables(t);
					     
					     System.out.println(tb.getName()+":�����ɹ���");
					 }
					
				}
			} else if(ch.getComm().charAt(0)=='s'||ch.getComm().charAt(0)=='S') {
				ch.checkSelect();
			}else if(ch.getComm().charAt(0)=='i'||ch.getComm().charAt(0)=='I') {
				ch.checkInsert();
			}else if(ch.getComm().charAt(0)=='d'||ch.getComm().charAt(0)=='D') {
				ch.checkDel();
			}
			else {
				throw new MyException("��������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		System.out.println("ллʹ�ã�");
	}
	 
}
