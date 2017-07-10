package app;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;

import app.Table;


public class DBApp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8836846456254249528L;
	String metaData = "metadata.csv";
	ArrayList<String> tableNames = new ArrayList<String>();
	
	
	public void readFromCSV()
	{
		String line = "";
		FileReader fileReader;
		try {
			fileReader = new FileReader(metaData);
			BufferedReader br = new BufferedReader(fileReader);
			while ((line = br.readLine()) != null) {
				String [] csvTables = line.split(",");
				if(!tableNames.contains(csvTables[0]))
					tableNames.add(csvTables[0]);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find file in CSV");
		} catch (IOException e) {
			System.err.println("Cannot read file from CSV");
		}
	}
	
	public void writeToCSV(String strTableName,Hashtable<String,String> htblColNameType,
			Hashtable<String,String> htblColNameRefs, String strKeyColName)
	{
		FileWriter wr;
		try {
			wr = new FileWriter(metaData, true);
			for(String key: htblColNameType.keySet())
			{
				wr.append(strTableName);
			    wr.append(',');
				wr.append(key);
				wr.append(',');
				wr.append(htblColNameType.get(key));
				wr.append(',');
				if(key.equals(strKeyColName))
					wr.append("True");
				else
					wr.append("False");
				wr.append(',');
				wr.append("False");
				wr.append(',');
				if(htblColNameRefs.contains(key))
					wr.append(htblColNameRefs.get(key));
				else
					wr.append(null);
				wr.append(',');
				Date d = new Date(System.currentTimeMillis());
				wr.append("" + d);
				wr.append('\n');
			}	
			wr.flush();
			wr.close();
			
		} catch (IOException e) {
			System.err.println("Cannot write to CSV!");
		}
	}
	
    public Table readTable(String name) throws FileNotFoundException, IOException, ClassNotFoundException
	{
	        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(name + ".class")));
	        Table t = (Table) ois.readObject();
			ois.close();
			return t;
	}
		

	public void init()
	{
		this.readFromCSV();
	}
	
    public void createTable(String strTableName, Hashtable<String,String> htblColNameType, 
                            Hashtable<String,String> htblColNameRefs, String strKeyColName) throws ClassNotFoundException, IOException
    {    	
    	if(this.tableNames.contains(strTableName))
    	{
    		throw new TableExistException("Table already exists");
    	}
    	else
    	{
    		htblColNameType.put("TouchDate", "Date");
    		Table t = new Table(strTableName,htblColNameType, htblColNameRefs, strKeyColName);
    		this.tableNames.add(strTableName);
        	this.writeToCSV(strTableName, htblColNameType, htblColNameRefs, strKeyColName);
        	this.createIndex(strTableName, strKeyColName);
    	}
    }
    
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws FileNotFoundException, ClassNotFoundException, IOException
    {
        if(this.tableNames.contains(strTableName))
        {
        	Table t = this.readTable(strTableName);
        	LinkedHashMap<String, Object> htblColNameValue2 = new LinkedHashMap<String, Object>();
        	htblColNameValue2.putAll(htblColNameValue);
        	
        	Date d = new Date(System.currentTimeMillis());
        	htblColNameValue2.put("TouchDate", d);
        	t.insert(htblColNameValue2);
        }
        else
        	throw new TableDoesNotExistException("Table doesn't exist");
    }
    
    public Iterator<?> selectFromTable(String strTable,Hashtable<String,Object> htblColNameValue, String strOperator) throws FileNotFoundException, ClassNotFoundException, IOException
    {
    	if(tableNames.contains(strTable))
    	{
    		 Table t = this.readTable(strTable);
    	     ArrayList<ArrayList<Object>> res = t.select(htblColNameValue,strOperator);
    	     if(res != null)
    	    	 return res.iterator();
    	     else
    	    	 return null;
    	}
    	else
    		throw new TableDoesNotExistException("Table doesn't exist");
    }
    
    public void updateTable(String strTableName,Object strKey,Hashtable<String,Object> htblColNameValue) throws FileNotFoundException, ClassNotFoundException, IOException
    {
    	if(this.tableNames.contains(strTableName))
    	{
    		Table t = this.readTable(strTableName);	
    		Date d = new Date(System.currentTimeMillis());
        	htblColNameValue.put("TouchDate", d);
    		t.update(strKey, htblColNameValue);
    	}
    	else
    		throw new TableDoesNotExistException("Table doesn't exist");
    }
    
    public void deleteFromTable(String strTable,Hashtable<String,Object> htblColNameValue, String strOperator) throws ClassNotFoundException, IOException{
    	if(tableNames.contains(strTable))
    	{
    		 Table t = this.readTable(strTable);
    	     t.delete(htblColNameValue,strOperator);
    	}
    	else
    		throw new TableDoesNotExistException("Table doesn't exist");
    }
    
    public void createIndex(String strTableName, String strColName) throws  ClassNotFoundException, IOException
    {
    	if(tableNames.contains(strTableName))
    	{
    		Table t = this.readTable(strTableName);
        	t.createIndexTable(strColName);
    	}
    	else
    		throw new TableDoesNotExistException("Table doesn't exist");
    }
    
}
