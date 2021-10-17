
/**
 * Die Macbook Klasse, um Macbook Objekte zu erzeugen, die von Kunden (Customer) gekauft werden können. Implementiert das Produkt
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class Macbook implements Product {

	private double preis;
	private String description = "Macbook Pro";
	private int amount;
	
	/**
	 * Konstruktor um einen Macbook Pro zu erzeugen
	 * @param preis Der Preis pro Stück
	 * @param amount Wie viele Macbook Pros am Anfang im Shop zur Verfügung stehen
	 */
	public Macbook(double preis, int amount) {
		this.preis = preis;
		this.amount = amount;
	}
	
	/**
	 * Konstruktor um einen beliebigen Macbook zu erzeugen
	 * @param preis Der Preis pro Stück
	 * @param amount Wie viele Macbook Pros am Anfang im Shop zur Verfügung stehen
	 * @param description Was für ein Macbook es sein solls
	 */
	public Macbook(double preis, int amount, String description) {
		this(preis, amount);
		this.description = description;
	}
	
	/**
	 * Getter Methode für den Preis
	 * @return Aktueller Preis als double
	 */
	public double getPreis() {
		return this.preis;
	}
	
	/**
	 * Getter Methode für den Description
	 * @return Aktueller Description (Produktname) als String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter Methode für den amount
	 * @return Aktuelle Menge im Shop als int
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * Produkte hinzufügen
	 * @param amount zu hinzufügende Menge
	 */
	public void addProduct(int amount) {
		this.amount += amount;
//		System.out.println(amount + " " + this.description + " are added into stock.");
	}
	
	/**
	 * Produkte entfernen
	 * @param amount zu entfernende Menge
	 */
	public void removeProduct(int amount) throws NotInStockException {
		if (this.amount - amount < 0) {
			throw new NotInStockException(this);			
		} else {
			this.amount -= amount;
//			System.out.println(amount + " " + this.description + " are removed from the stock.");					
		}
	}
}
