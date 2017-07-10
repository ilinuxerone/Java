package app;

import java.io.File;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Observer;
import java.util.Properties;


public class Table implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3916193428116405815L;
	String tableName;
	ArrayList<String> pageNames;
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<String> indexNames;
	ArrayList<BTree> indices;
	String primaryKey;
	LinkedHashMap<String,String> htblColNameType = new LinkedHashMap<String,String>();
	Hashtable<String,String> htblColNameRefs = new Hashtable<String,String>();
	int rowsPerPage = this.getMaxRows();
	
	public Table(String strTableName, Hashtable<String,String> htblColNameType,
			Hashtable<String,String> htblColNameRefs, String strKeyColName)
	{
		this.tableName = strTableName;
		this.pageNames = new ArrayList<String>();
		this.indexNames = new ArrayList<String>();
		this.indices = new ArrayList<BTree>();
		this.primaryKey = strKeyColName;
		
		for(String key: htblColNameType.keySet())
		{
			this.htblColNameType.put(key, htblColNameType.get(key));
			this.columnNames.add(key);
		}
		for(String key: htblColNameRefs.keySet())
			this.htblColNameRefs.put(key, htblColNameRefs.get(key));
		this.saveTable();
	}
	
	public void insert(LinkedHashMap<String, Object> htblColNameValue) throws ClassNotFoundException, IOException
	{
		int currPageNo = this.pageNames.size();
		String pageName;
		
		//1st insertion
		if(currPageNo == 0)
		{
			pageName = this.tableName + currPageNo;
			Page p = new Page(pageName,this.rowsPerPage,this.primaryKey,htblColNameType);
			p.insertIntoPage(htblColNameValue);
			this.pageNames.add(pageName);
			
			for(int i = 0; i < this.indexNames.size(); i++)
			{
				Object BTreeKey = htblColNameValue.get(this.indexNames.get(i));
				BTree BTreeIndex  = this.indices.get(i);
				BTreeIndex.insert((Comparable) BTreeKey, pageName);
			}
			this.saveTable();
		}
		else
		{
			pageName = this.pageNames.get(this.pageNames.size() - 1);
			Page p = this.readPage(pageName);
			String pn = p.pageName;
			
			//full page
			//stry removing - 1
			if(p.currentRow == this.rowsPerPage - 1)
			{
				pageName = this.tableName + currPageNo++;
				Page p2 = new Page(pageName,this.rowsPerPage,this.primaryKey,htblColNameType);
				p2.insertIntoPage(htblColNameValue);
				this.pageNames.add(pageName);
			}
			else
			{
				p.insertIntoPage(htblColNameValue);
			}
			for(int i = 0; i < this.indexNames.size(); i++)
			{
				Object BTreeKey = htblColNameValue.get(this.indexNames.get(i));
				BTree BTreeIndex  = this.indices.get(i);
				BTreeIndex.insert((Comparable) BTreeKey, pageName);
			}
			this.saveTable();
		}
	}
	
	public ArrayList<ArrayList<Object>> select(Hashtable<String,Object> htblColNameValue, String strOperator) throws ClassNotFoundException, IOException
	{
		//Look for a column that's indexed
		 String foundIndex = "None";
		 for(String key: htblColNameValue.keySet())
		 {
			 if(this.indexNames.indexOf(key) > -1)
			 {
				 foundIndex = key;
				 break;
			 }
		 }
		 ArrayList<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		 
		 //There exists an index for the column i'm selecting from => search using BTree
		 if(!foundIndex.equals("None"))
		 {
			 int index = this.indexNames.indexOf(foundIndex);
			 BTree bt = this.indices.get(index);
			 String pageName = (String) bt.search((Comparable) htblColNameValue.get(foundIndex));
			 Page p = this.readPage(pageName);
			 ArrayList<ArrayList<Object>> r = p.selectFromPage(htblColNameValue, strOperator);
			 
			if(r != null && !r.isEmpty())
			{
				int j = 0;
				ArrayList<Object> allRows = r.get(0);
				for(int i = 0; i < allRows.size(); )
				{
					ArrayList<Object> al = new ArrayList<Object>();
					res.add(al);
					for(j = 0 ; j < this.columnNames.size(); j++)
					{
						al.add(allRows.get(i));
						i++;
					}
				}
			}
			return res; 
		 }
		 else //Linear search 3ady
		 {
				for(int i = 0; i < this.pageNames.size(); i++)
				{
					String pageName = this.pageNames.get(i);
					Page p = this.readPage(pageName);
					if(p.currentRow > 0)
					{
						ArrayList<ArrayList<Object>> r = p.selectFromPage(htblColNameValue,strOperator);
						
						if(r != null)
						{
							for (int j = 0; j < r.size(); j++) {
								res.add(r.get(j));
							}			
						}
					}
					return res;
			}
			return null;
		 }
	}
	
	public void update(Object strKey, Hashtable<String,Object> htblColNameValue) throws ClassNotFoundException, IOException
	{
		int index = this.indexNames.indexOf(this.primaryKey);
		BTree bt = this.indices.get(index);
		String pageName = (String) bt.search((Comparable) strKey);
		
		if(pageName != null)
		{
			Page p = this.readPage(pageName);
			Object[] updatedRow = p.updateInPage(strKey, htblColNameValue);
			
			//Update values in BTree indices
			for(String key: htblColNameValue.keySet())
			{
				if(this.indexNames.contains(key) && updatedRow != null)
				{
					for(int i = 0; i < updatedRow.length; i++)
					{
						Object updatedValue = updatedRow[this.columnNames.indexOf(key)];
						BTree BTreeIndex = this.indices.get(this.indexNames.indexOf(key));
						BTreeIndex.delete((Comparable) updatedValue);
						BTreeIndex.insert((Comparable) htblColNameValue.get(key), pageName);
					}
				}
			}
		}
		else
			System.out.println("Records to update not found.");
		
	}
	
	public void delete(Hashtable<String,Object> htblColNameValue, String strOperator) throws ClassNotFoundException, IOException{
		
		 String foundIndex = "None";
		 for(String key: htblColNameValue.keySet())
		 {
			 if(this.indexNames.indexOf(key) > -1)
			 {
				 foundIndex = key;
				 break;
			 }
		 }	
		 
		 ArrayList<Object[]> deletedRows = new ArrayList<Object[]>();
		 if(!foundIndex.equals("None"))
		 {
			 int index = this.indexNames.indexOf(foundIndex);
			 BTree bt = this.indices.get(index);
			 String pageName = (String) bt.search((Comparable) htblColNameValue.get(foundIndex));
			 if(pageName != null)
			 {
				 Page p = this.readPage(pageName);
				 ArrayList<Object[]> deletedFromPage = p.deleteFromPage(htblColNameValue, strOperator);
				 if(deletedFromPage != null)
				 {
					 for(int i = 0; i < deletedFromPage.size(); i++)
					 {
						 deletedRows.add(deletedFromPage.get(i).clone());
					 }
				 }
			 }
			 else
				 System.out.println("Records to delete not found.");
		 }
		 else
		 {
			//linear deletion with no BTree indices
			for(int i = 0; i < this.pageNames.size(); i++)
			{
				String pageName = this.pageNames.get(i);
				Page p = this.readPage(pageName);
				
				ArrayList<Object[]> deletedFromPage = p.deleteFromPage(htblColNameValue,strOperator);
				if(deletedFromPage != null)
				 {
					 for(int j = 0; j < deletedFromPage.size(); j++)
					 {
						 deletedRows.add(deletedFromPage.get(j).clone());
					 }
				 }
			}
		 }
		 //delete from all BTree indices
		 for(int i = 0; i < this.indexNames.size(); i++)
		 {
			 for(int j = 0; j < deletedRows.size(); j++)
			 {
				 Object[] row = deletedRows.get(j);
				 Object objectToDelete = row[this.columnNames.indexOf(this.indexNames.get(i))];
				 BTree bt = this.indices.get(i);
				 bt.delete((Comparable) objectToDelete);
			 }
		 }
	}
	
	public void createIndexTable(String strColName) throws ClassNotFoundException, IOException
	{
		if(this.indexNames.contains(strColName))
		{
			System.out.println("Index on " + strColName + " column has already been created.");
			return;
		}
		BTree index = new BTree();
		this.indexNames.add(strColName);
		if(!this.indices.isEmpty())
		{
			//System.out.println("Creating secondary index on column " + strColName);
			for(int i = 0; i < this.pageNames.size(); i++)
			{
				Page p = this.readPage(this.pageNames.get(i));
				for(int j = 0; j < p.currentRow ; j++)
				{
					int colIndex = p.columnNames.indexOf(strColName);
					Object o = p.data[j][colIndex];
					index.insert((Comparable) o, p.pageName);
				}
			}
		}
		this.indices.add(index);
		this.saveTable();
	}
	
	public Page readPage(String name) throws ClassNotFoundException, IOException
	{
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(name + ".class")));
            Page p = (Page) ois.readObject();
			ois.close();
		return p;
	} 
	
	public void saveTable()
	{
		try {
	   		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File( this.tableName + ".class")));
			oos.writeObject(this); 		
			oos.close();
		} catch (IOException e) {
			System.err.println("Problem saving table to disk");
		}
	}
	
	public int getMaxRows()
	{
		int rows= 0;
		Properties prop = new Properties();
		
		// the configuration file name
        String fileName = "Config/DBApp.properties";            
        InputStream is;
		try {
			is = new FileInputStream(fileName);
			prop.load(is);
			is.close();
			
			Enumeration keys = prop.keys();
			String k = (String)keys.nextElement();
			String value= prop.getProperty(k);
			rows = Integer.valueOf(value);
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}

        return rows;
	}
	
	
	public static void main(String [] args)
	{
		
	}

}
