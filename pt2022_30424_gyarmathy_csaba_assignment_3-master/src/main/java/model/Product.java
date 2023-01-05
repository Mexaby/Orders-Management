package model;

/**
 * Product class
 * Each parameter corresponds to a column from the database table
 *
 * @author Csabi
 */
public class Product {
    private int productId;
    private String productName;
    private int price;
    private int stock;

    /**
     * Constructor
     *
     * @param productId
     * @param productName
     * @param price
     * @param stock
     */
    public Product(int productId, String productName, int price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
