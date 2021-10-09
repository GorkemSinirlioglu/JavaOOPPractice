
public class Macbook implements Product {
	private double preis;
	private String description = "Macbook Pro";
	private int amount;
	
	public Macbook(double preis, int amount) {
		this.preis = preis;
		this.amount = amount;
	}
	
	public Macbook(double preis, int amount, String description) {
		this(preis, amount);
		this.description = description;
	}
	
	public double getPreis() {
		return this.preis;
	}
	public String getDescription() {
		return this.description;
	}
	public int getAmount() {
		return this.amount;
	}
	public void addProduct(int amount) {
		this.amount += amount;
//		System.out.println(amount + " " + this.description + " are added into stock.");
	}
	public void removeProduct(int amount) throws NotInStockException {
		if (this.amount - amount < 0) {
			throw new NotInStockException(this);			
		} else {
			this.amount -= amount;
//			System.out.println(amount + " " + this.description + " are removed from the stock.");					
		}
	}
}
