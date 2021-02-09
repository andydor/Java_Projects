package model;

/**
 * clasa ce modeleaza un produs
 */
public class Product {
	private int id;
	/**
	 * nume produs
	 */
	private String name;
	/**
	 * pret
	 */
	private double price;
	/**
	 * cantitate stoc
	 */
	private int quantity;

	/**
	 * nume, pret si cantitate stoc
	 * @param nume
	 * @param price2
	 * @param quantity
	 */
	public Product(String nume, double price2, int quantity) {
		this.name = nume;
		this.price = price2;
		this.quantity = quantity;
	}

	/**
	 *
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param nume
	 */
	public void setName(String nume) {
		this.name = nume;
	}

	/**
	 *
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *
	 * @param pret
	 */
	public void setPrice(double pret) {
		this.price = pret;
	}

	/**
	 * toString pentru detalii produs
	 * @return
	 */
	public String toString() {
		return "Product [id = " + id + ", name = " + name + ", price = " + price + ", quantity = " + quantity + "]";
	}
}