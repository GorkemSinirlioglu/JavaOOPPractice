import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Die Customer sfds, erbt vom People, hat einige spezielle Attribute wie Bankkonto, Liste gekaufter Artikel.
 * Stellt ein statisches Attribut zur Verfügung, wo alle Kunden des Shops gelistet werden können
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class Customer extends People implements Comparable<Customer> {
	

	private static List<Customer> Customers = new LinkedList<>();
	private double bankaccount;
	private List<Product> ProductsBought = new LinkedList<>();
	
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
	 * Liefert die Liste aus gekauften Produkten des Kunden
	 * @return List<Product> ProductsBought gekauften Produkte
	 */
	public List<Product> getProductsBought() {
		return ProductsBought;
	}
	
	public void setProductsBought(List<Product> newProducts) {
		ProductsBought = newProducts;
	}
	
	/**
	 * Kaufen eines Produkts
	 * @param product Produktobjekt das gekauft werden soll
	 * @param numToBuy Anzahl zu kaufen
	 * @throws CustomerNoMoneyException falls nicht genug Geld auf dem Konto
	 */
	public double buy(Product product, int numToBuy) throws CustomerNoMoneyException {
		if (this.bankaccount < product.getPreis() * numToBuy) {
			throw new CustomerNoMoneyException(this);
		}
		
		try {
			product.removeProduct(numToBuy);
			this.ProductsBought.add(product);
			this.bankaccount -= product.getPreis() * numToBuy;
			System.out.println(this.name + " buys " + numToBuy + " " + product.getDescription() + ".");
			return product.getPreis() * numToBuy;
		} catch (NotInStockException ex) {
			System.out.println(this.name + " was trying to buy " + numToBuy + " " + product.getDescription() + ". " + ex.getMessage());
			return 0;
		}

	}
	
	/**
	 * Gibt die Details zum Kunden in einer passenden Form aus
	 * @return 
	 */
	public String getDescription() {
		StringBuffer sb = new StringBuffer();
		if (ProductsBought.size() != 0) {
			sb.append("\n" + this.name + " has bought following items from our shop:");
			for (Product product : ProductsBought) {
				sb.append("\n" + "- " + product.getDescription());
			}
		}
		sb.append("\n" + this.name + " has " + this.bankaccount + " left in the bank account.");
		sb.append("\n" + this.name + " recides in " + this.address + " and is born in " + new SimpleDateFormat("dd MMMM yyyy").format(this.birthday) + "\n");
		return sb.toString();
	}
	
	/**
	 * Gibt den Namen des Kunden aus
	 * @return 
	 */
	@Override
	public String toString() {
		return this.getName();
	}
	
	/**
	 * Prüft obt ein gegebenes Objekt dem Kundenobjekt gleich ist. Berücksichtigt wird
	 * name, address, birthday, bankaccount, ProductsBought
	 * @param obj beliebiges Objekt
	 * @return boolean true falls Objekte inhaltlich gleich, sonst false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Customer) {
			Customer c = (Customer) obj;
			return (name.equals(c.getName()) && address.equals(c.getAddress())
					&& birthday.equals(c.getBirthday()) && bankaccount == c.getBankaccount() 
					&& ProductsBought.equals(c.getProductsBought()));
		} else {
			return super.equals(obj);
		}
	}
	
	/**
	 * Erzeugt eine Hashcode aus Stringkonkatenation von ProductsBought, bankaccount, name, address, birthday
	 * Aus so entstehendem String wird das hashCode gebildet. Primzahl 31 wird benutzt (Java Standard)
	 */
	@Override
	public int hashCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(ProductsBought.toString());
		sb.append(bankaccount);
		sb.append(getName());
		sb.append(getAddress());
		sb.append(getBirthday());
		String s = sb.toString();
		
		return s.hashCode();
	}
	
	/**
	 * Statische Methode um Kundenobjekte zu klonen
	 * @param c zu klonendes Objekt
	 * @return Customer Objekt als Klon
	 */
	public static Customer clone(Customer c) {
		Customer clone = new Customer(c.getName(), c.getAddress(), c.getBirthday(), c.getBankaccount());
		clone.setProductsBought(c.getProductsBought());
		return clone;
	}
	
	/**
	 * Vergleicht Kunden anhand Bankkonto
	 * @param Customer c Kundenobjekt das mit dem Kunden auf dem die Methode aufgerufen wird, verglichen werden soll
	 * @return int Ganzzahl entweder 0, oder negative, oder positiv:
	 * <ul>
	 * <li>0 -> Bankkonten gleich</li>
	 * <li>negativ -> Übergebener Kunde hat mehr Geld als Kunde auf dem die Methode aufgerufen wird</li>
	 * <li>positiv -> Kunde auf dem die Methode aufgerufen wird hat mehr Geld als übergebener Kunde</li>
	 * </ul>
	 */
	@Override
	public int compareTo(Customer c) {
		double difference = bankaccount - c.getBankaccount();
		return (int) difference;
	}

	
	

}
