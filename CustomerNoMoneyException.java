/**
 * Die Ausnahme falls Kunde nicht genügend Geld hat um die Bestellung zu tätigen
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class CustomerNoMoneyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CustomerNoMoneyException(Customer customer) {
		super(customer.getName() + "'s bank account is not sufficient for this purchase!");
	}
}
