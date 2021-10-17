/**
 * Das Product Interface, legt ein Produkt fest
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public interface Product {
	
	public double getPreis();
	public String getDescription();
	public int getAmount();
	public void addProduct(int amount);
	public void removeProduct(int amount) throws NotInStockException;

}
