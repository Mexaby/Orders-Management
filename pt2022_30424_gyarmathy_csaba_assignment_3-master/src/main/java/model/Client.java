package model;

/**
 * Client class
 * Each parameter is a column from the database table
 *
 * @author Csabi
 */
public class Client {
    private int clientId;
    private String clientName;
    private String address;
    private int age;
    private String email;

    /**
     * Constructor
     *
     * @param clientId
     * @param clientName
     * @param address
     * @param age
     * @param email
     */
    public Client(int clientId, String clientName, String address, int age, String email) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
