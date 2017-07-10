package app;

public class TableDoesNotExistException extends DBEngineException {

	
	public TableDoesNotExistException(){
		super();
	}
	
	
	public TableDoesNotExistException(String m){
		super(m);
	}
}
