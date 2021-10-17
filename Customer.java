import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Die Customer Klasse, erbt vom People, hat einige spezielle Attribute wie Bankkonto, Liste gekaufter Artikel.
 * Stellt ein statisches Attribut zur Verfügung, wo alle Kunden des Shops gelistet werden können
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class Customer extends People {
	

	private double bankaccount;
	private List<Product> ProductsBought = new LinkedList<>();
	private static List<Customer> Customers = new LinkedList<>();
	
	/**
	 * Konstruktor zum Erzeugen eines Kunden
	 * @param name Voller Name als String
	 * @param address Addresse als String
	 * @param birthday Geburtsdatum als Date
	 * @param bankaccount Geld auf dem Konto als double
	 */
	public Customer(String name, String address, Date birthday, double bankaccount) {
		super(name, address, birthday);
		this.bankaccount = bankaccount;
		Customer.Customers.add(this);
	}
	
	/**
	 * Getter für das Bankkonto (Geld auf dem Konto)
	 * @return Geld auf dem Konto in double
	 */
	public double getBankaccount() {
		return this.bankaccount;
	}
	
	/**
	 * Kaufen eines Produkts
	 * @param product Produktobjekt das gekauft werden soll
	 * @param numToBuy Anzahl zu kaufen
	 * @throws CustomerNoMoneyException falls nicht genug Geld auf dem Konto
	 */
	public void buy(Product product, int numToBuy) throws CustomerNoMoneyException {
		if (this.bankaccount < product.getPreis() * numToBuy) {
			throw new CustomerNoMoneyException(this);
		}
		
		try {
			product.removeProduct(numToBuy);
			this.ProductsBought.add(product);
			this.bankaccount -= product.getPreis();
			System.out.println(this.name + " buys " + numToBuy + " " + product.getDescription() + ".");
		} catch (NotInStockException ex) {
			System.out.println(this.name + " was trying to buy " + numToBuy + " " + product.getDescription() + ". " + ex.getMessage());
		}

	}
	
	/**
	 * Gibt den Kunden in einer passenden Form aus
	 */
	public void printCustomer() {
		System.out.println("\n" + this.name + " has bought following items from our shop:");
		this.ProductsBought.forEach((Product product) -> {
			System.out.println("- " + product.getDescription());
		});
		System.out.println(this.name + " has " + this.bankaccount + " left in the bank account.");
		System.out.println(this.name + " recides in " + this.address + " and is born in " + new SimpleDateFormat("dd MMMM yyyy").format(this.birthday));
	}

	
	

}
