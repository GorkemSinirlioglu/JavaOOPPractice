
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Die OnlineShop Klasse, hier wird das Shop verwaltet
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class OnlineShop {
	
	
	/**
	 * Konvertiert ein Jahr/Monat/Tag in die Sekunden seit Epoch
	 * @param year
	 * @param month
	 * @param date
	 * @return Sekunden seit Epoch als long
	 */
	private static long returnSeconds(int year, int month, int date) {
	    return LocalDate.of(year, month, date).atStartOfDay(ZoneId.of("UTC")).toEpochSecond()*1000;
	}
	
	/**
	 * Statische Methode für eine Kaufaktion
	 * @param customer Kunde das kauft
	 * @param product Produkt das gekauft wird
	 * @param numToBuy Anzahl wie viel gekauft wird
	 */
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
