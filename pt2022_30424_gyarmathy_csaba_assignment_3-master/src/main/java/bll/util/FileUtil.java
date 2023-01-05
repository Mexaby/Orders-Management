package bll.util;

import model.Client;
import model.Order;
import model.Product;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Helper class that creates a text file with a bill for an order
 *
 * @author Csabi
 */
public class FileUtil {
    /**
     * Creates text file with given data
     *
     * @param client
     * @param order
     * @param product
     */
    public static void createBill(Client client, Order order, Product product) {
        try {
            PrintWriter writer = new PrintWriter("bill" + order.getOrderId() + ".txt", StandardCharsets.UTF_8);
            writer.println("Order nr. " + order.getOrderId());
            writer.println("Customer: " + client.getClientName());
            writer.println("E-Mail: " + client.getEmail());
            writer.println("Age: " + client.getAge());
            writer.println("Address: " + client.getAddress());
            writer.println("---------------------------");
            writer.println("Product: " + product.getProductName());
            writer.println("Unit price: " + product.getPrice());
            writer.println("Order quantity: " + order.getOrderQuantity());
            writer.println("---------------------------");
            writer.println("Total price: " + order.getOrderQuantity() * product.getPrice());

            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to create text file");
        }
    }
}
