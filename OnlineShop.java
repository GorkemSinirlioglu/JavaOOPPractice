
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Scanner s = new Scanner(System.in);
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
		Scanner s = new Scanner(System.in);
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
		Scanner s = new Scanner(System.in);
		System.out.print(message + ": ");
		String result = s.next();
		return result.equals(acceptableString);
	}
	
	/**
	 * Liefert ein Datum zurück. Zwingt den Nutzer solange zur Eingabe, bis ein gültiger Datum ermittelt wurde.
	 * @param message Aufforderungsnachricht an Nutzer
	 * @return Date Datum
	 */
	public static Date getDate(String message) {
		System.out.println(message + "\n------------------\n");
		boolean success = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date returnDate = null;
		do {
			Scanner s = new Scanner(System.in);
			try {
				System.out.print("Bitte ein Datem in Form DD.MM.YYYY eingeben: ");
				String d = s.next();
				returnDate = sdf.parse(d);
				
				success = true;
			} catch (ParseException e) {
				log.error("Falsches Datumformat: " + e.getMessage());
			}
		} while (!success);
		
		return returnDate;
	}
	
	/**
	 * Liest einen String vom Nutzer ein
	 * @param message Aufforderungsnachricht
	 * @return String aus Eingabe vom Nutzer
	 */
	public static String getString(String message) {
		Scanner s = new Scanner(System.in);
		System.out.print(message + ": ");
		String result = s.next();
		return result;
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
		System.out.println("We have " + moneyInBank + " in the account.");
	}
	
	public static void purchase() {
		if (macbooks.size() == 0) System.out.println("Keine Produkte im Shop!");
		else if (customers.values().size() == 0) System.out.println("Keine Kunden!");
		else {	
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
	}
	
	public static void onlineShop() {
		log.info("Started");
		
		
		while (getBool("0", "0 um Produkt zu erzeugen, Beliebige andere Eingabe zum Fortzufahren")) {
			String name = getString("Welches Macbook-Model soll hinzugefügt werden?");
			int stock = (int) Math.ceil(0.1 + (Math.random() * 99));
			double price = Math.floor(Math.random() * 2500);
			
			Macbook m = new Macbook(price, stock, name);
			macbooks.add(m);
		}
		

		while (getBool("0", "0 um Kunde zu erzeugen, Beliebige andere Eingabe zum Fortzufahren")) {
			String name = getString("Kundenname eingeben");
			String address = getString("Kundenadresse eingeben");
			Date birth = getDate("Geburtsdatum des Kunden eingeben");
			double bankaccount = Math.floor(Math.random() * 100000);
			
			Customer c = new Customer(name, address, birth, bankaccount);
			customers.put(c.hashCode(), c);
			
			
		}
		
		
		
		do {
			printShopStatus();
			purchase();
		} while (!getBool("0", "0 zum Abbrechen, Beliebige andere Eingabe zum Fortzufahren"));
		
		
		
		
		log.info("Compile successfull");
	}
	

	
	public static void testThings() {
		
	}

	
	public static void main(String[] args) {
		
		
  		onlineShop();
		

		
	}

}
