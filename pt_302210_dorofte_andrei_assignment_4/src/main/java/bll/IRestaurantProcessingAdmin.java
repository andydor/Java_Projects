package bll;

public interface IRestaurantProcessingAdmin {
    /**
     * Metoda pentru creare MenuItem de tip Base
     * @param name
     * @param price
     */
    public void createMenuItemBase(String name, double price);

    /**
     * Metoda pentru creare MenuItem de tip Composite
     * @param name
     */
    public void createMenuItemComposite(String name);

    /**
     * Metoda pentru stergere MenuItem
     * @param name
     */
    public void deleteMenuItem(String name);

    /**
     * Metoda pentru editare nume/pret MenuItem
     * @param nameold
     * @param name
     * @param price
     */
    public void editMenuItem(String nameold, String name, double price);
}
