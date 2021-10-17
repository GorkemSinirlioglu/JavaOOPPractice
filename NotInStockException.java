/**
 * Die Ausnahme falls die zu bestellende Menge nicht im Shop vorhanden
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class NotInStockException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NotInStockException(Product product) {
		super(product.getDescription() + " is out of stock!");
	}
	
}
