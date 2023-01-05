package dao;

import connection.DatabaseConnection;
import model.Client;

import java.sql.*;
import java.util.ArrayList;

/**
 * Client database access object
 * Contains basic methods for client operations
 *
 * @author Csabi
 */
public class ClientDAO {
    private Statement clientStatement;
    private static final ClientDAO instance = new ClientDAO();

    /**
     * Returns instance of ClientDAO object
     *
     * @return
     */
    public static ClientDAO getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public ClientDAO() {
        try {
            Connection con = DatabaseConnection.getConnection();
            this.clientStatement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a list of Client objects from the database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Client> getClientsList() throws SQLException {
        ResultSet rs = clientStatement.executeQuery("SELECT * FROM clients");

        ArrayList<Client> clientsList = new ArrayList<>();
        while (rs.next()) {
            Client newClient = new Client(rs.getInt("client_id"),
                    rs.getString("client_name"),
                    rs.getString("address"),
                    rs.getInt("age"),
                    rs.getString("email"));
            clientsList.add(newClient);
        }
        return clientsList;
    }

    /**
     * Returns a client with a given ID
     *
     * @param client_id
     * @return
     * @throws SQLException
     */
    public Client getClient(int client_id) throws SQLException {
        ResultSet rs = clientStatement.executeQuery("SELECT * FROM clients WHERE client_id = " + client_id);
        Client newClient = new Client(rs.getInt("client_id"),
                rs.getString("client_name"),
                rs.getString("address"),
                rs.getInt("age"),
                rs.getString("email"));
        return newClient;
    }

    /**
     * Adds client to the database
     * The parameters correspond to the columns of the database
     *
     * @param client_id
     * @param client_name
     * @param email
     * @param age
     * @param address
     * @throws SQLException
     */
    public void insertClient(int client_id, String client_name, String email, int age, String address) throws SQLException {
        String insertSql = "INSERT INTO clients(client_id, client_name, email, age, address) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(insertSql);
        preparedStatement.setInt(1, client_id);
        preparedStatement.setString(2, client_name);
        preparedStatement.setString(3, email);
        preparedStatement.setInt(4, age);
        preparedStatement.setString(5, address);
        preparedStatement.executeUpdate();
    }

    /**
     * Deletes a client with a given ID
     *
     * @param client_id
     * @throws SQLException
     */
    public void deleteClient(int client_id) throws SQLException {
        String deleteSql = "DELETE FROM clients WHERE client_id =" + client_id;
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(deleteSql);
        preparedStatement.executeUpdate();
    }

    /**
     * Edits client
     *
     * @param client_id
     * @param client_name
     * @param email
     * @param age
     * @param address
     * @throws SQLException
     */
    public void editClient(int client_id, String client_name, String email, int age, String address) throws SQLException {
        String editSql = "UPDATE clients SET " +
                "client_id = " + client_id + "," +
                "client_name = '" + client_name + "', " +
                "address = '" + address + "', " +
                "age = " + age + ", " +
                "email = '" + email + "' " +
                "WHERE client_id = " + client_id;
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(editSql);
        preparedStatement.executeUpdate();
    }

    /**
     * Returns true if client with given ID has an ongoing order, false otherwise
     *
     * @param client_id
     * @return
     * @throws SQLException
     */
    public boolean existsClientOrder(int client_id) throws SQLException {
        ResultSet rs = clientStatement.executeQuery("SELECT * FROM orders WHERE client_id = " + client_id);
        return rs.isBeforeFirst();
    }

    /**
     * Returns ture if client with given ID exists, false otherwise
     *
     * @param client_id
     * @return
     * @throws SQLException
     */
    public boolean existsClientID(int client_id) throws SQLException {
        ResultSet rs = clientStatement.executeQuery("SELECT * FROM clients WHERE client_id = " + client_id);
        return rs.isBeforeFirst();
    }
}
