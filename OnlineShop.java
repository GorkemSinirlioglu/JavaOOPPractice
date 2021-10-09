
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class OnlineShop {
	

	private static long returnSeconds(int year, int month, int date) {
	    return LocalDate.of(year, month, date).atStartOfDay(ZoneId.of("UTC")).toEpochSecond()*1000;
	}
	
	private static void buyAction(Customer customer, Product product, int numToBuy) {
		try {
			customer.buy(product, numToBuy);
		} catch (CustomerNoMoneyException ex) {
			System.out.println(customer.getName() + " was trying to buy " + 
							   numToBuy + " " + product.getDescription() + 
							   ". " + ex.getMessage());
		}
	}

	
	public static void main(String[] args) {
		Macbook StandardMacbook = new Macbook(950, 55);
		Macbook MacbookM1 = new Macbook(1240, 30, "Macbook M1");
		Customer c1 = new Customer("John Smith", "BlablaStreet 5", new Date(returnSeconds(1955, 1, 30)), 25000);
		Customer c2 = new Customer("Tom Hirsch", "GuStr 51", new Date(returnSeconds(1975, 12, 22)), 1);

		buyAction(c1, StandardMacbook, 2);
		buyAction(c2, MacbookM1, 1);

		c1.printCustomer();
		c2.printCustomer();
	
		
	}

}
