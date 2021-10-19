
/**
 * Falls die Nutzereingabe nicht wie gewünscht war
 * @author gorkemsinirlioglu
 * @version 2.3.5
 */
public class WrongInputException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public WrongInputException() {
		super();
	}
	public WrongInputException(String info) {
		super(info);
	}

}
