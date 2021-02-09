package model;

/**
 * clasa ce modeleaza clientul
 */
public class Client {
	private int id;
	/**
	 * nume client
	 */
	private String name;
	/**
	 * adresa client
	 */
	private String address;

	/**
	 * constructor cu nume si adresa
	 * @param name
	 * @param address
	 */
	public Client(String name, String address) {
		this.name = name;
		this.address = address;
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
	 * getter pentru nume
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter pentru nume
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter pentru adresa
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * setter pentru adresa
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * metoda toString pentru afisare detalii client
	 * @return
	 */
	public String toString() {
		return "Client [id = " + id + ", name = " + name + ", address = " + address + "]";
	}
}