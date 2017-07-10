package src.com.utitl.check;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import sun.tools.jar.resources.jar;

import src.com.ht.data.Data;
import src.com.ht.entity.Table;
import src.com.ht.entity.Tables;
import src.com.ht.exception.MyException;
import src.com.util.comm.Tools;

public class Checker {
	private String comm = null;
	public Checker(String s) throws MyException{
		this.comm = Tools.delSpaceEnter(s).trim();
		checkMatch();
	}

	public void checkMatch() throws MyException{
		int len = comm.length();
		int[] stack = new int[comm.length()];
		int top=-1;
		for(int i=0;i<len;i++) {
			if(comm.charAt(i)=='(') {
				stack[++top] = '(';
			} else if(comm.charAt(i)==')') {
				if(top>=0) {
					top--;
				} else {
					throw new MyException("���Ų�ƥ�䣡��"); 
				}
			}
		}

	}
	public Table checkCreate() throws MyException{
		String[] words = comm.split("[\\s]");
		Table table  = new Table();

		if(!words[1].toUpperCase().equals("TABLE")){
			throw new MyException("�޷����� ��������䣡");
		}
		String na = "";
		for(int i=13;i<comm.length();i++) {
			if(comm.charAt(i)=='(') break; 
			na+=comm.charAt(i);
		}

		if(!na.trim().matches("[a-zA-Z_]+")) {

			throw new MyException(na+" ����������Ч��ֻ��Ϊ��Сд��ĸ�»��ߣ�");
		}
		//���ñ���
		table.setName(na.trim());
		String st="";
		for(int i=0;i<comm.length();i++) {
			if(comm.charAt(i)=='(') {
				st = comm.substring(i+1,comm.length()-1);
				break;
			}
		}
		if(st.equals("")){
			throw new MyException("���Ų�ƥ�䣡");
		}
		String[] w = st.split(",");
		for(int i=0;i<w.length;i++) {
			String name = Tools.delSpaceEnter(w[i]).trim();
			String[] c = name.split("\\s");
			if(c.length!=2) {
				throw new MyException(w[i]+":���Խ�������");
			}else {
				if(c[0].trim().matches("[a-zA-Z0-9_]+")==false||
						(c[0].charAt(0)>='0'&&c[0].charAt(0)<='9')){
					throw new MyException(c[0]+":���������岻�Ϸ�");
				} else {
					if(c[1].trim().matches("int")) {
						table.getMp().put(c[0].trim(),"int");
					} else if(c[1].trim().matches("char\\([0-9]+\\)")){
						table.getMp().put(c[0].trim(), c[1].trim());
					} else {
						throw new MyException(c[1]+":�������Ͳ��Ϸ���");
					}
				}
			}
		}
		return table;
	}
	public static void main(String[] args) throws MyException {
		Checker ch = new Checker(" insert into wocha(name,age) values('df',8)");

		ch.checkInsert();
	}

	public boolean checkInsert() throws MyException{
		String[] words = this.comm.split("\\s");
		if(words[0].toLowerCase().equals("insert")&&words[1].toLowerCase().equals("into")) {
			String name = "";
			int index=0;
			for(int i=12;i<comm.length();i++) {
				if(comm.charAt(i)=='(') {
					name=name.trim();
					index=i+1;
					break;
				}
				name+=comm.charAt(i);
			}
			Table t = new Table();
			t.setName(name);
			if(!Tools.readTables().getTables().contains(t)) {
				throw new MyException(name+":���������ڣ����飡");
			}

			Tables tbs = Tools.readTables();
			Table table = null;
			for (Table tb : tbs.getTables()) {
				if(tb.getName().equals(name)) {
					table = tb;
					break ;
				}
			}
			String colm = "";
			for(int i=index;i<comm.length();i++) {
				if(comm.charAt(i)==')') {
					index= i+1;
					break;
				}
				colm+=comm.charAt(i);
			}
			String[] cs = colm.split(",");

			String val ="" ;
			for(int i=index;i<comm.length();i++) {
				if(comm.charAt(i)=='(') {
					index=i+1;
					break;
				}
				val+=comm.charAt(i);
			}
			val=val.trim();
			if(val.toLowerCase().equals("values")==false){
				throw new MyException("��������");
			}
			String num="";
			for(int i=index;i<comm.length();i++) {
				if(comm.charAt(i)==')') {
					break;
				}
				num+= comm.charAt(i);
			}
			String[] vals=num.split(",");
			List<Object> va = new ArrayList<Object>();
			if(cs.length!=vals.length) {
				throw new MyException("�����ֵ����Ӧ���飡");
			}
			for(int i=0;i<cs.length;i++) {
				String k=table.getMp().get(cs[i].trim());
				if(null==k){
					throw new MyException(cs[i]+":���Բ����ڣ����飡");
				} else {
					if(k.equals("int")) {
						if(vals[i].trim().matches("[0-9]+")==false) {
							throw new MyException(vals[i].trim()+":�����ֵ���������Ͳ�һ�£�����ʧ��");
						}else {
							va.add(Integer.valueOf(vals[i]));
						}
					} else {
						vals[i]=vals[i].trim();
						if(vals[i].charAt(0)!='\''||vals[i].charAt(vals[i].length()-1)!='\'') {
							throw new MyException(vals[i].trim()+":�����ֵ���������Ͳ�һ�£�����ʧ��");
						}
						va.add(vals[i]); 
					}

				}
			}
			Data dt = Tools.readData(name+".tb");
			if(dt==null) {
				dt = new Data();
			}
			List<Table> tbs2=Tools.readTables().getTables();
			Table tbn = null;
			for (Table tb2 : tbs2) {
				if(tb2.getName().equals(name)) {
					tbn = tb2;
					break;
				}
			}

			Set s = tbn.getMp().keySet();
			Iterator i = s.iterator();

			while(i.hasNext()){

				Object k = i.next();
				for(int j=0;j<cs.length;j++) {
					if(cs[j].trim().equals((String)k)){
						//						System.out.println("#");
						if(va.get(j) instanceof String) {
							String oi=(String)(va.get(j));
							oi=oi.replace("'", "");
							dt.getData().add(oi);
						}
						else 
							dt.getData().add(va.get(j));
						break;
					}
				}
			}

			//			for (Object object : va) {
			//				dt.getData().add(object);
			//			}
			Tools.writeData(dt, name+".tb");
			System.out.println("���ݲ���ɹ���");
		} else {
			throw new MyException("�޷������������");
		}
		return true;
	}
	public boolean checkDel() throws MyException{
		//delete from name where cnamm=value;
		int num=0;
		String [] w = comm.split("\\s");
		if(w[0].trim().toLowerCase().equals("delete")==false) {
			throw new MyException("����������!ɾ��ʧ�ܣ�");
		} else if(w[1].trim().toLowerCase().equals("from")==false){
			throw new MyException("fromȱʧ��ɾ��ʧ�ܣ�");
		} 
		Tables tbs = Tools.readTables();
		if(w.length<3) {
			throw new MyException("�������㣡ɾ��ʧ�ܣ�");
		} else if(w.length==3){
			if(tbs.getTables().contains(new Table(w[2].trim()))==false) {
				throw new MyException("�����������飡~");
			} else {
				File file = new File(w[2].trim()+".tb");  
				if(file.exists()){
					file.delete();
					System.out.println("ɾ���ɹ���");
				}
			}
		} else {
			if(tbs.getTables().contains(new Table(w[2].trim()))==false) {
				throw new MyException("�����������飡~");
			}
			Table t = null;
			for (Table tb : tbs.getTables()) {
				if(tb.getName().equals(w[2].trim())) {
					t = tb;
					break;
				}
			}

			if(w[3].trim().equals("where")==false) {
				throw new MyException("��������");
			}
			String last = this.comm.split("where")[1].trim();
			String [] condition = last.split(",");
			int a[] = new int[t.getMp().size()+2];
			List<Object> ans = new ArrayList<Object>();
			for(int i=0;i<condition.length;i++) {
				String [] kv = condition[i].trim().split("=");
				if(kv.length!=2) {
					throw new MyException(condition[i]+"where���������������飡");
				}
				kv[0]= kv[0].trim();
				kv[1]= kv[1].trim();
				if(t.getMp().get(kv[0])==null) {
					throw new MyException(kv[0]+":���������ڣ�");
				} 
				if(t.getMp().get(kv[0]).equals("int")) {
					if(kv[1].matches("[0-9]+")==false) {
						throw new MyException(kv[1]+":�붨���е����Բ��������飡");
					} else {
						///code
						Set s = t.getMp().keySet();
						Iterator it = s.iterator();
						int de=0;

						while(it.hasNext()){
							Object k = it.next();
							String u = (String)k;
							if(u.equals(kv[0])) {
								a[de]=1;
								ans.add(kv[1]);
								break;
							}
							de++;
						}

					}
				} else {
					if(!(kv[1].charAt(0)=='\''&&kv[1].charAt(kv[1].length()-1)=='\'')){
						throw new MyException(kv[1]+":�붨���е����Բ��������飡");
					} else {
						kv[1]=kv[1].replace("'", "");
						//code
						Set s = t.getMp().keySet();
						Iterator it = s.iterator();
						int de=0;

						while(it.hasNext()){
							Object k = it.next();
							String u = (String)k;
							if(u.equals(kv[0])) {
								if(a[de]==1) {
									throw new MyException("��������");
								}
								a[de]=1;
								ans.add(kv[1]);
								break;
							}
							de++;
						}
					}

				}
			}
			Data dt = Tools.readData(t.getName()+".tb");
			int [] stack = new int[333];
			int top=-1;
			for(int i=0;i<dt.getData().size();i+=t.getMp().size()) {
				int ind=0;
				int ret=0;
				for(int j=i;j<i+t.getMp().size();j++) {
					if(a[j-i]==1) {
						if(dt.getData().get(j) instanceof Integer ) {

							if(((Integer)dt.getData().get(j)).equals(Integer.valueOf((String)ans.get(ind)))){
								ret++;

							}
						}
						if(dt.getData().get(j) instanceof String) {
							if(((String)(dt.getData().get(j))).equals((String)ans.get(ind))){
								ret++;
							}
						}
						ind++;
					}
				}
				if(ret==ans.size()){
					stack[++top] = i/t.getMp().size();
				}
			}
			int yu=0;
		    num=(top+1);
			for(int i=0;i<dt.getData().size();i+=t.getMp().size()) {
				if(yu<=top&&stack[yu]==(int)(i/t.getMp().size())) {
					for(int j=i;j<i+t.getMp().size();j++){
						dt.getData().set(j, null);
					}
					yu++;
				}
			}
		    List<Integer> nullArr = new ArrayList<Integer>();  
			nullArr.add(null);  
			dt.getData().removeAll(nullArr);
			Tools.writeData(dt,t.getName()+".tb");

		}

		System.out.println(num+" ����¼ɾ���ɹ���");
		return true;
	}
	public boolean checkSelect() throws MyException{
		String[] w=comm.split("\\s");
		if(w[0].trim().toLowerCase().equals("select")==false) {
			throw new MyException("�����������飡");
		}
		if(w[1].trim().equals("*")){
			if(w[2].trim().equals("from")==false) {
				throw new MyException("�����������飡");
			}
			if(Tools.readTables().getTables().contains(new Table(w[3].trim()))==false) {
				throw new MyException(w[3]+":�����ڣ����飡");
			}

			Table tb = null;
			for (Table	t : Tools.readTables().getTables()) {
				if(t.getName().equals(w[3].trim())) {
					tb = t;
					break;
				}
			}

			/*
			   create table kl(name char(3),age int);
			   insert into kl(name,age) values('d',3);
			   insert into kl(name,age) values('d',3);
			   insert into kl(name,age) values('d',3);
			   insert into kl(name,age) values('d',3);
			   insert into kl(name,age) values('d',322);
			   insert into kl(name,age) values('d',23);
			   select * from kl;
			 */
			Set<?> s = tb.getMp().keySet();
			Iterator<?> ii = s.iterator();
			System.out.println(w[3]+" : ");
			while(ii.hasNext()){
				Object k = ii.next();
				System.out.print(k+"     ");
			}
			System.out.println();
			Data dt = Tools.readData(w[3].trim()+".tb");
			if(dt!=null)
				System.out.println("ѡ����ˣ�"+dt.getData().size()/tb.getMp().size()+" ����¼��");
			else {
				System.out.println("ѡ����ˣ�"+0+" ����¼��");
				return true;
			}
			int size=dt.getData().size();
			for(int i=0;i<size;i++) {
				System.out.print(dt.getData().get(i)+"   ");
				if((i+1)%tb.getMp().size()==0&&i!=0) {
					System.out.println();
				}
			}

		} else {
			String[] cls = comm.split("from");
			if(cls.length<2) {
				throw new MyException("��������ȱʧfrom��");
			}

			String tnm = cls[1].trim().split("\\s")[0].trim();
			Tables ts = Tools.readTables();
			if(ts.getTables().contains(new Table(tnm))==false) {
				throw new MyException(tnm+":�����ڣ�");
			}
			Table t = new Table();
			cls[0] = cls[0].substring(7).trim();
			String [] p = cls[0].split(","); 
			for(int i=0;i<ts.getTables().size();i++) {
				if(ts.getTables().get(i).getName().equals(tnm)) {
					t = ts.getTables().get(i);
					break;
				}
			}
			for(int i=0;i<p.length;i++) {
				if(t.getMp().get(p[i].trim())==null) {
					System.out.print(p[i].trim());
					throw new MyException(":���������ڣ����飡");
				}
			}
			Set<?> s = t.getMp().keySet();
			Iterator<?> ii = s.iterator();
			System.out.println(tnm+" : ");
			int[]  a = new int[22];
			int ui=0;
			while(ii.hasNext()){
				Object k = ii.next();
				for(int i=0;i<p.length;i++) {
					if(p[i].trim().equals((String)k)){
						System.out.print(p[i].trim()+"       ");
						a[ui]=1;
						break;
					}
				}
				ui++;
			}
			System.out.println("");
			Data dt = Tools.readData(tnm.trim()+".tb");
			System.out.println("ѡ����ˣ�"+dt.getData().size()/t.getMp().size()+" ����¼��");
			int size=dt.getData().size();
			for(int i=0;i<size;i++) {
				if(a[i%t.getMp().size()]==1){
					System.out.print(dt.getData().get(i)+"   ");
				}
				if((i+1)%t.getMp().size()==0&&i!=0) {
					System.out.println();
				}
			}



		}
		return false;
	}

	public boolean checkUpdate(){
		return false;
	}

	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
}

/*
#########��ӭʹ�ü������ݿ�#########
create table ui(name char(23),age int) values('oij',44);
age int) values('oij':���Խ�������
com.ht.exception.MyException
	at com.utitl.check.Checker.checkCreate(Checker.java:75)
	at com.ht.db.DB.main(DB.java:38)
create table ui(name char(23),age int)  ;
ui:�����ɹ���~
insert into ui(name ,age) values('bd',44);

���ݲ���ɹ���
insert into ui(name ,age) values('bd1',442);
���ݲ���ɹ���
insert into ui(name ,age) values('bd2',42);
���ݲ���ɹ���
insert into ui(name ,age) values('bd3',14);
���ݲ���ɹ���
insert into ui(name ,age) values('bd2',44);
���ݲ���ɹ���
insert into ui(name ,age) values('b2d',44);
���ݲ���ɹ���
select * from ui;
ui : 
name     age     
ѡ����ˣ�6 ����¼��
bd   44   
bd1   442   
bd2   42   
bd3   14   
bd2   44   
b2d   44   
delete from ui where age=44;
ɾ���ɹ���
select * from ui;
ui : 
name     age     
ѡ����ˣ�5 ����¼��
null   bd1   
442   bd2   
42   bd3   
14   bd2   
44   b2d   
44   
\
 */