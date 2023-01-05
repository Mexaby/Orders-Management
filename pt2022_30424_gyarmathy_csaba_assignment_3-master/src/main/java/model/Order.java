package model;

/**
 * Order class
 * Each parameter corresponds to a column from the database table
 *
 * @author Csabi
 */
public class Order {
    private int orderId;
    private int productId;
    private int clientId;
    private int orderQuantity;

    /**
     * Constructor
     *
     * @param orderId
     * @param productId
     * @param clientId
     * @param orderQuantity
     */
    public Order(int orderId, int productId, int clientId, int orderQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.clientId = clientId;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
}
