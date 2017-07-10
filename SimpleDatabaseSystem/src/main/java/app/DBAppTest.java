package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Iterator;

public class DBAppTest {
	
	public static void main (String [] args) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		        //create a new DBApp
				DBApp myDB = new DBApp();
				myDB.init();
				
				// creating table "Faculty"
				
				Hashtable<String, String> fTblColNameType = new Hashtable<String, String>();
				fTblColNameType.put("ID", "Integer");
				fTblColNameType.put("Name", "String");
		 
				Hashtable<String, String> fTblColNameRefs = new Hashtable<String, String>();
		 
				myDB.createTable("Faculty", fTblColNameType, fTblColNameRefs, "ID");
		 
				// creating table "Major"
		 
				Hashtable<String, String> mTblColNameType = new Hashtable<String, String>(); 
				mTblColNameType.put("ID", "Integer");
				mTblColNameType.put("Name", "String");
				mTblColNameType.put("Faculty_ID", "Integer");
		 
				Hashtable<String, String> mTblColNameRefs = new Hashtable<String, String>();
				mTblColNameRefs.put("Faculty_ID", "Faculty.ID");
		 
				myDB.createTable("Major", mTblColNameType, mTblColNameRefs, "ID");
		 
				// creating table "Course"
		 
				Hashtable<String, String> coTblColNameType = new Hashtable<String, String>();
				coTblColNameType.put("ID", "Integer");
				coTblColNameType.put("Name", "String");
				coTblColNameType.put("Code", "String");
				coTblColNameType.put("Hours", "Integer");
				coTblColNameType.put("Semester", "Integer");
				coTblColNameType.put("Major_ID", "Integer");
		 
				Hashtable<String, String> coTblColNameRefs = new Hashtable<String, String>();
				coTblColNameRefs.put("Major_ID", "Major.ID");
		 
				myDB.createTable("Course", coTblColNameType, coTblColNameRefs, "ID");
		 
				// creating table "Student"
		 
				Hashtable<String, String> stTblColNameType = new Hashtable<String, String>();
				stTblColNameType.put("ID", "Integer");
				stTblColNameType.put("First_Name", "String");
				stTblColNameType.put("Last_Name", "String");
				stTblColNameType.put("GPA", "Double");
				stTblColNameType.put("Age", "Integer");
		 
				Hashtable<String, String> stTblColNameRefs = new Hashtable<String, String>();
		 
				myDB.createTable("Student", stTblColNameType, stTblColNameRefs, "ID");
		 
				// creating table "Student in Course"
		 
				Hashtable<String, String> scTblColNameType = new Hashtable<String, String>();
				scTblColNameType.put("ID", "Integer");
				scTblColNameType.put("Student_ID", "Integer");
				scTblColNameType.put("Course_ID", "Integer");
		 
				Hashtable<String, String> scTblColNameRefs = new Hashtable<String, String>();
				scTblColNameRefs.put("Student_ID", "Student.ID");
				scTblColNameRefs.put("Course_ID", "Course.ID");
		 
				myDB.createTable("Student_in_Course", scTblColNameType, scTblColNameRefs, "ID");
		 
				// insert in table "Faculty"
		 
				Hashtable<String, Object> ftblColNameValue1 = new Hashtable<String, Object>();
				ftblColNameValue1.put("ID", Integer.valueOf("1"));
				ftblColNameValue1.put("Name", "Media Engineering and Technology");
				myDB.insertIntoTable("Faculty", ftblColNameValue1);
		 
				Hashtable<String, Object> ftblColNameValue2 = new Hashtable<String, Object>();
				ftblColNameValue2.put("ID", Integer.valueOf("2"));
				ftblColNameValue2.put("Name", "Management Technology");
				myDB.insertIntoTable("Faculty", ftblColNameValue2);
		 
				for (int i = 0; i < 1000; i++) {
					Hashtable<String, Object> ftblColNameValueI = new Hashtable<String, Object>();
					ftblColNameValueI.put("ID", Integer.valueOf(("" + (i + 2))));
					ftblColNameValueI.put("Name", "f" + (i + 2));
					myDB.insertIntoTable("Faculty", ftblColNameValueI);
				}
		 
				// insert in table "Major"
		 
				Hashtable<String, Object> mtblColNameValue1 = new Hashtable<String, Object>();
				mtblColNameValue1.put("ID", Integer.valueOf("1"));
				mtblColNameValue1.put("Name", "Computer Science & Engineering");
				mtblColNameValue1.put("Faculty_ID", Integer.valueOf("1"));
				myDB.insertIntoTable("Major", mtblColNameValue1);
		 
				Hashtable<String, Object> mtblColNameValue2 = new Hashtable<String, Object>();
				mtblColNameValue2.put("ID", Integer.valueOf("2"));
				mtblColNameValue2.put("Name", "Business Informatics");
				mtblColNameValue2.put("Faculty_ID", Integer.valueOf("2"));
				myDB.insertIntoTable("Major", mtblColNameValue2);
		 
				for (int i = 0; i < 1000; i++) {
					Hashtable<String, Object> mtblColNameValueI = new Hashtable<String, Object>();
					mtblColNameValueI.put("ID", Integer.valueOf(("" + (i + 2))));
					mtblColNameValueI.put("Name", "m" + (i + 2));
					mtblColNameValueI.put("Faculty_ID", Integer.valueOf(("" + (i + 2))));
					myDB.insertIntoTable("Major", mtblColNameValueI);
				}
		 
				// insert in table "Course"
		 
				Hashtable<String, Object> ctblColNameValue1 = new Hashtable<String, Object>();
				ctblColNameValue1.put("ID", Integer.valueOf("1"));
				ctblColNameValue1.put("Name", "Data Bases II");
				ctblColNameValue1.put("Code", "CSEN 604");
				ctblColNameValue1.put("Hours", Integer.valueOf("4"));
				ctblColNameValue1.put("Semester", Integer.valueOf("6"));
				ctblColNameValue1.put("Major_ID", Integer.valueOf("1"));
				myDB.insertIntoTable("Course", ctblColNameValue1);
		 
				Hashtable<String, Object> ctblColNameValue2 = new Hashtable<String, Object>();
				ctblColNameValue2.put("ID", Integer.valueOf("1"));
				ctblColNameValue2.put("Name", "Data Bases II");
				ctblColNameValue2.put("Code", "CSEN 604");
				ctblColNameValue2.put("Hours", Integer.valueOf("4"));
				ctblColNameValue2.put("Semester", Integer.valueOf("6"));
				ctblColNameValue2.put("Major_ID", Integer.valueOf("2"));
				myDB.insertIntoTable("Course", ctblColNameValue2);
		 
				for (int i = 0; i < 1000; i++) {
					Hashtable<String, Object> ctblColNameValueI = new Hashtable<String, Object>();
					ctblColNameValueI.put("ID", Integer.valueOf(("" + (i + 2))));
					ctblColNameValueI.put("Name", "c" + (i + 2));
					ctblColNameValueI.put("Code", "co " + (i + 2));
					ctblColNameValueI.put("Hours", Integer.valueOf("4"));
					ctblColNameValueI.put("Semester", Integer.valueOf("6"));
					ctblColNameValueI.put("Major_ID", Integer.valueOf(("" + (i + 2))));
					myDB.insertIntoTable("Course", ctblColNameValueI);
				}
		 
				// insert in table "Student"
		 
				for (int i = 0; i < 500; i++) {
					Hashtable<String, Object> sttblColNameValueI = new Hashtable<String, Object>();
					sttblColNameValueI.put("ID", Integer.valueOf(("" + i)));
					sttblColNameValueI.put("First_Name", "FN" + i);
					sttblColNameValueI.put("Last_Name", "LN" + i);
					sttblColNameValueI.put("GPA", Double.valueOf("0.7"));
					sttblColNameValueI.put("Age", Integer.valueOf("20"));
					myDB.insertIntoTable("Student", sttblColNameValueI);
				}
		 
				 // creating secondary index on table Student
				 myDB.createIndex("Student", "Age");
				 
				 // selecting student with id = 3
				 Hashtable<String, Object> stblColNameValue = new Hashtable<String,Object>();
				 stblColNameValue.put("ID",Integer.valueOf(3));
		 
				 long startTime = System.currentTimeMillis();
				 Iterator<?> myIt = myDB.selectFromTable("Student", stblColNameValue,"or");
				 
				 long endTime = System.currentTimeMillis();
				 long totalTime = endTime - startTime;
				 System.out.println(totalTime);
				 
				 while (myIt.hasNext()) {
				  System.out.println(myIt.next());
				 }

				// update age of student with id = 50 
				Hashtable<String,Object> stblColNameValue2 = new Hashtable<String,Object>();
				stblColNameValue2.put("Age", Integer.valueOf("23"));
				myDB.updateTable("Student",50,stblColNameValue2);
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Student0.class")));
		        Page p = (Page)ois.readObject();
				ois.close();
				for(int i = 0; i < p.currentRow; i++)
				{
					for(int j = 0; j <p.data[i].length; j++)
						System.out.print(p.data[i][j] + " ");
					System.out.println("");
				}				
				
				//delete & select to check the deletion
				Hashtable<String,Object> stblColNameValueDelete = new Hashtable<String, Object>();
				stblColNameValueDelete.put("Age", Integer.valueOf(20));
				myDB.deleteFromTable("Student", stblColNameValueDelete, "or");
				
				 Hashtable<String, Object> stblColNameValueI = new Hashtable<String,Object>();
				 stblColNameValueI.put("ID",Integer.valueOf(3));
				 Iterator<?> It = myDB.selectFromTable("Student", stblColNameValueI,"or");
				 
				 while (It.hasNext()) {
					  System.out.println(It.next());
				 }
				
	}

}
