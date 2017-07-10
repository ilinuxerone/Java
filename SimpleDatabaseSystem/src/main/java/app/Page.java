package app;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import javax.swing.plaf.synth.SynthSeparatorUI;


public class Page implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6307912464597391971L;
	String pageName;
	int rows;
	int columns;
	int currentRow;
	String primaryKey;
	LinkedHashMap<String,String> htblColNameType = new LinkedHashMap<String,String>();
    ArrayList<String> columnNames = new ArrayList<String>();
    ArrayList<Boolean> deleted = new ArrayList<Boolean>();
	Object[][] data;
	
	public Page(String pageName, int rows, String primaryKey,LinkedHashMap<String,String> htblColNameType)
	{
		this.pageName = pageName;
		this.rows = rows;
		this.columns = htblColNameType.size();
		this.primaryKey= primaryKey;
		this.currentRow= 0;
		for(String key: htblColNameType.keySet())
		{
			this.htblColNameType.put(key, htblColNameType.get(key));
			this.columnNames.add(key);
		}
		//Collections.reverse(this.columnNames);
		this.data = new Object[this.rows][this.columns];
		this.savePage();
	}
	
	public String insertIntoPage(LinkedHashMap<String, Object> htblColNameValue)
	{
		boolean typeCheck = true;
		for(String key: htblColNameValue.keySet())
		{
			String typeInserted = htblColNameValue.get(key).getClass().getSimpleName();
			String type = this.htblColNameType.get(key);
			if(!type.equals(typeInserted))
				typeCheck = false;
		}
		if(typeCheck)
		{
			for(String key: htblColNameValue.keySet())
			{
				int col = this.columnNames.indexOf(key);
				this.data[this.currentRow][col] = htblColNameValue.get(key);
				deleted.add(false);
				col++;
			}
			this.currentRow++;
			this.savePage();
			return this.pageName;
		}
		else
			throw new InvalidInputException("Unsupported Type Insertion");

	} 
	
	public ArrayList<ArrayList<Object>> selectFromPage(Hashtable<String,Object> htblColNameValue, String strOperator) throws ClassNotFoundException
	{
		ArrayList<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		ArrayList<Object>row = new ArrayList<Object>();
	
		for(int i = 0; i < this.currentRow; i++)
		{
			boolean AND = true;
			boolean OR = false;
			
			for(String key: htblColNameValue.keySet())
			{			
				int colIndex = columnNames.indexOf(key);
				
				Object k = 	this.data[i][colIndex];				
				if((strOperator.equalsIgnoreCase("and") || strOperator.equals("&")) && AND)
				{
					if(!k.equals(htblColNameValue.get(key)))
					{
						AND = false;
					}
				}
				else
				{
					if(strOperator.equalsIgnoreCase("or") || strOperator.equals("|"))
					{
						if(k.equals(htblColNameValue.get(key)))
						{
							OR = true;
						}
					}
				}
			}
			if((OR && (strOperator.equalsIgnoreCase("or") || strOperator.equals("|"))) 
					|| (AND && (strOperator.equalsIgnoreCase("and") || strOperator.equals("&"))))	
			{
				if(!deleted.get(i))
				{
					for(int j = 0; j < this.columns; j++){
						row.add(this.data[i][j]);
					}
					res.add(row);
				}
			}
		}
		return res;
	}
	
	public Object[] updateInPage(Object strKey,Hashtable<String,Object> htblColNameValue)
	{
	    Object[] updatedRow = new Object[this.columns];
		
		int primaryIndex = this.columnNames.indexOf(this.primaryKey);
		for(int i = 0; i < this.currentRow; i++)
		{
			//found a match with the primary key
			if(this.data[i][primaryIndex].equals(strKey))
			{
				boolean typeCheck = true;	
				for(String key: htblColNameValue.keySet())
				{
					String typeInserted = htblColNameValue.get(key).getClass().getSimpleName();
					String type = this.htblColNameType.get(key);
					
					if(!type.equals(typeInserted))
						typeCheck = false;
				}
				if(typeCheck)
				{
					for(String key: htblColNameValue.keySet())
					{
						updatedRow = this.data[i].clone();
						int colIndex = this.columnNames.indexOf(key);
						this.data[i][colIndex] = htblColNameValue.get(key);
					}
				}
				else
					throw new InvalidInputException("Unsupported type update");
			}
		}
		this.savePage();
		return updatedRow;
	}
	
	public ArrayList<Object[]> deleteFromPage(Hashtable<String,Object> htblColNameValue, String strOperator){
		ArrayList<Object[]> totalDeleted = new ArrayList<Object[]>();
		Object[] row = new Object[this.columns];		
		
		for(int i = 0; i < this.currentRow; i++)
		{
			boolean AND = true;
			boolean OR = false;
			
			for(String key: htblColNameValue.keySet())
			{			
				int colIndex = columnNames.indexOf(key);
				Object k = 	this.data[i][colIndex];
				
				if((strOperator.equalsIgnoreCase("and") || strOperator.equals("&")) && AND)
				{
					if(!k.equals(htblColNameValue.get(key)))
					{
						AND = false;
					}
				}
				else
				{
					if(strOperator.equalsIgnoreCase("or") || strOperator.equals("|"))
					{
						if(k.equals(htblColNameValue.get(key)))
						{
							OR = true;
					        //System.out.println("found!");
						}
					}
				}
			}
			if((OR && strOperator.equalsIgnoreCase("or") || strOperator.equals("|")) 
					|| (AND && strOperator.equalsIgnoreCase("and") || strOperator.equals("&")))
			{
			    row = this.data[i].clone();
				deleted.set(i, true);
				totalDeleted.add(row);
			}
		}
		this.savePage();
		return totalDeleted;
	}
	
	
	public void savePage()
	{
	   	try {
	   		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.pageName + ".class")));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			System.err.println("Problem saving page to disk");
		}
	} 
	
	
	public static void main(String [] args) throws ClassNotFoundException
	{
		/*Hashtable<String,String> htblColNameType = new Hashtable<String,String>();
		htblColNameType.put("ID", "Integer");
		htblColNameType.put("Name", "String");
		htblColNameType.put("Age", "Integer");
		
		Page p = new Page("Studentz", 5, "ID" ,htblColNameType);
		
		Hashtable<String,Object> htblColNameValue = new Hashtable<String, Object>();
		htblColNameValue.put("ID", Integer.valueOf(1));
		htblColNameValue.put("Name", "Potato");
		htblColNameValue.put("Age", Integer.valueOf(20));
		p.insertIntoPage(htblColNameValue);
		
		Hashtable<String,Object> htblColNameValue2 = new Hashtable<String, Object>();
		htblColNameValue2.put("ID", Integer.valueOf(2));
		htblColNameValue2.put("Name", "fakes");
		htblColNameValue2.put("Age", Integer.valueOf(20));
		p.insertIntoPage(htblColNameValue2);
		
		for(int j =0; j < p.deleted.size(); j++)
			System.out.println(p.deleted.get(j) + " ");
		System.out.println("---------------");
		
		for(int i = 0; i < p.data.length; i++)
		{
			for(int j = 0; j < p.data[i].length; j++)
				System.out.print(p.data[i][j] + " ");
			System.out.println("");
		}
		System.out.println("---------------");
		
		Hashtable<String,Object> htblColNameValueSelect = new Hashtable<String, Object>();
		htblColNameValueSelect.put("Age", Integer.valueOf(20));
		p.selectFromPage(htblColNameValueSelect, "OR");
		
		Hashtable<String,Object> htblColNameValueUpdate = new Hashtable<String,Object>();
		htblColNameValueUpdate.put("ID", Integer.valueOf(2));
		Object key = (Object) Integer.valueOf(1);
		p.updateInPage(key,htblColNameValueUpdate);
		
		
		Hashtable<String,Object> htblColNameValueDelete = new Hashtable<String,Object>();
		htblColNameValueDelete.put("ID", Integer.valueOf(1));
		p.deleteFromPage(htblColNameValueDelete, "or");
		
		for(int i = 0; i < p.data.length; i++)
		{
			for(int j = 0; j < p.data[i].length; j++)
				System.out.print(p.data[i][j] + " ");
			System.out.println("");
		}
		System.out.println("---------------");
		
		
		for(int j =0; j < p.deleted.size(); j++)
			System.out.println(p.deleted.get(j) + " ");
		System.out.println("---------------");
	*/
	}

}
