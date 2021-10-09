
public class NotInStockException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NotInStockException(Product product) {
		super(product.getDescription() + " is out of stock!");
	}
	
}
