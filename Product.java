
public interface Product {
	
	public double getPreis();
	public String getDescription();
	public int getAmount();
	public void addProduct(int amount);
	public void removeProduct(int amount) throws NotInStockException;

}
