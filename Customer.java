import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Customer extends People {
	

	public double bankaccount;
	public List<Product> ProductsBought = new LinkedList<>();
	public static List<Customer> Customers = new LinkedList<>();
	
	public Customer(String name, String address, Date birthday, double bankaccount) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.bankaccount = bankaccount;
		Customer.Customers.add(this);
	}
	

	public double getBankaccount() {
		return this.bankaccount;
	}
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
	public void printCustomer() {
		System.out.println("\n" + this.name + " has bought following items from our shop:");
		this.ProductsBought.forEach((Product product) -> {
			System.out.println("- " + product.getDescription());
		});
		System.out.println(this.name + " has " + this.bankaccount + " left in the bank account.");
		System.out.println(this.name + " recides in " + this.address + " and is born in " + new SimpleDateFormat("dd MMMM yyyy").format(this.birthday));
	}

	
	

}
