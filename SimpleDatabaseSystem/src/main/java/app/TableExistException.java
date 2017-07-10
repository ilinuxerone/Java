package app;

public class TableExistException extends DBEngineException{

	
	public TableExistException(){
		super();
	}
	
	public TableExistException(String m){
		super(m);
	}
}
