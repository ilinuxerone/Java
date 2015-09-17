package org.Dom4j;

import org.dom4j.Document;

public interface Dom4jDAOintf {
	public Document getDocument(String filePath) throws Exception;	
	
	public void add(Document document) throws Exception;
	
	public void delete(Document document)throws Exception;
	
	public void update(Document document)throws Exception;
	
	public void find(Document document)throws Exception;
	
	public void tranverse(Document document)throws Exception;
}
