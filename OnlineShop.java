
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.*;

/**
 * Die OnlineShop Klasse, hier wird das Shop verwaltet
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class OnlineShop {
	
	public static Logger log = LogManager.getRootLogger();
	public static Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
	public static List<Macbook> macbooks = new LinkedList<Macbook>();
	public static double moneyInBank = 0;
	public static Scanner s = new Scanner(System.in);
	
	
	
	
	/**
	 * Statische Methode für eine Kaufaktion
	 * @param customer Kunde das kauft
	 * @param product Produkt das gekauft wird
	 * @param numToBuy Anzahl wie viel gekauft wird
	 */
	private static void buyAction(Customer customer, Product product, int numToBuy) {
		try {
			moneyInBank += customer.buy(product, numToBuy);
		} catch (CustomerNoMoneyException ex) {
			System.out.println(customer.getName() + " was trying to buy " + 
							   numToBuy + " " + product.getDescription() + 
							   ". " + ex.getMessage());
		}
	}
	
	/**
	 * Nutzereingabe als int in einem definierten Bereich min:max (Beide eingeschlossen)
	 * @param min Minimalwert der Nutzereingabe
	 * @param max Maximalwert der Nutzereingabe
	 * @param message Aufferdorungsnachricht an Nutzer
	 * @return Nutzereingabe als int
	 * @throws WrongInputException falls Nutzereingabe nicht im zulässigen Bereich
	 */
	public static int getInt(int min, int max, String message) throws WrongInputException {
		System.out.print(message + " (Minimum: " + min + ", Maximum: " + max + "): ");
		int result = s.nextInt();
		if (result < min || result > max) throw new WrongInputException("Bitte einen Wert zwischen " + min + " und " + max + " eingeben.");
		
		return result;
	}
	
	/**
	 * Nutzereingabe aus einer definierten Menge (keyset)
	 * @param keyset Gültige Schlüssel
	 * @param message Aufferdorungsnachricht an Nutzer
	 * @return Nutzereingabe als int
	 * @throws WrongInputException falls Nutzereingabe nicht in keyset
	 */
	public static int getInt(Set<Integer> keyset, String message) throws WrongInputException {
		System.out.print(message + " (Darf eine aus der Liste sein: " + keyset.toString() + "): ");
		int result = s.nextInt();
		if (!keyset.contains(result)) throw new WrongInputException("Ungültiger Schlüssel.");
		return result;
	}
	
	/**
	 * Gibt true oder false zurück je nach Nutzereingabe
	 * @param acceptableString Akzeptiertes String
	 * @param message Aufforderungsnachricht an Nutzer
	 * @return boolean Bei Eingabe von acceptableString wird true ausgegeben sonst false
	 */
	public static boolean getBool(String acceptableString, String message) {
		System.out.print(message + ": ");
		String result = s.next();
		return result.equals(acceptableString);
	}
	
	/**
	 * Listet index und Namen (aus toString() Methode) aus einer vorgegebenen Sammlung
	 * @param liste zu listende Liste
	 */
	public static void listElements(Collection<?> liste) {
		int index = 0;
		for(Object elem : liste) {
			System.out.println("Index: " + index++ + "\tName: " + elem.toString());
		}
	}
	
	/**
	 * Listet Schlüssel und Name (aus toString() Methode) aus einem vorgegebenen Map
	 * @param liste die zu listende Sammlung
	 */
	public static void listElements(Map<Integer, ?> liste) {
		for(Integer i : liste.keySet()) {
			System.out.println("Schlüssel: " + i + "\tName: " + liste.get(i).toString());
		}
	}
	
	/**
	 * Gibt die aktuelle Situation im Shop aus
	 */
	public static void printShopStatus() {
		for (Customer c : customers.values()) {
			System.out.println(c.getDescription());
		}
	}
	
	public static void purchase() {
		try {
		listElements(macbooks);
		int macbookIndex = getInt(0, macbooks.size() - 1, "Bitte Macbook auswählen, das gekauft werden soll");
		listElements(customers);
		int customerKey = getInt(customers.keySet(), "Bitte Kundenschlüssel eingeben, von dem Kunden, der kaufen soll");
		int numToBuy = getInt(0, macbooks.get(macbookIndex).getAmount(), "Wie viel soll gekauft werden" );
		
		buyAction(customers.get(customerKey), macbooks.get(macbookIndex),numToBuy);
		
		} catch(WrongInputException e) {
			log.error(e.getMessage());
		} finally {
			printShopStatus();
		}
	}

	@SuppressWarnings ("deprecation")
	public static void main(String[] args) {
		
		log.info("Started");
		
		Macbook StandardMacbook = new Macbook(950, 55);
		Macbook MacbookM1 = new Macbook(1240, 30, "Macbook M1");
		
		Customer c1 = new Customer("John Smith", "BlablaStreet 5", new Date(55, 1, 28, 0, 0, 0), 25000);
		Customer c2 = new Customer("Tom Hirsch", "GuStr 51", new Date(75, 11, 22, 0, 0, 0), 10000);
		
		macbooks.add(StandardMacbook);
		macbooks.add(MacbookM1);
		customers.put(c1.hashCode(), c1);
		customers.put(c2.hashCode(), c2);
		
		
		do {
			printShopStatus();//
			purchase();
		} while (!getBool("0", "0 zum Abbrechen, Beliebige andere Eingabe zum Fortzufahren"));
		
		s.close();
		log.info("Compile successfull");
		
	}

}
