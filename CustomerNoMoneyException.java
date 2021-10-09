
public class CustomerNoMoneyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CustomerNoMoneyException(Customer customer) {
		super(customer.getName() + "'s bank account is not sufficient for this purchase!");
	}
}
