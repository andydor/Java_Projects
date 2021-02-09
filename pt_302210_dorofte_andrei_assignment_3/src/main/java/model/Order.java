package model;

/**
 * clasa ce modeleaza o comanda
 */
public class Order {
	private int id;
	/**
	 * nume client
	 */
	private String name;
	/**
	 * produs dorit
	 */
	private String product;
	/**
	 * cantitate produs
	 */
	private int quantity;
	/**
	 * pret final, utilizat doar in orderDAO
	 */
	private double final_price;

	/**
	 * constructor cu nume client, nume produs si cantitate produs
	 * @param name
	 * @param product
	 * @param quantity
	 */
	public Order(String name, String product, int quantity) {
		this.name = name;
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * getter pentru id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter pentru id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter pentru pret final comanda
	 * @return
	 */
	public double getFinal_price() {
		return final_price;
	}

	/**
	 *
	 * @param d
	 */
	public void setFinal_price(double d) {
		this.final_price = d;
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
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public String getProduct() {
		return product;
	}

	/**
	 *
	 * @param product
	 */
	public void setProduct(String product) {
		this.product = product;
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
	 * toString pentru detalii comanda
	 * @return
	 */
	public String toString() {
		return "Order [name = " + name + ", product = " + product + ", quantity = " + quantity + "]";
	}
}