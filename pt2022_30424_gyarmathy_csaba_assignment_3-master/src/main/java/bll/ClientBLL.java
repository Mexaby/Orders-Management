package bll;

import bll.util.TableUtil;
import bll.validators.ClientValidator;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Client business logic layer
 * Contains the logic of the CRUD methods with validators
 *
 * @author Csabi
 */
public class ClientBLL {
    private static final ClientBLL instance = new ClientBLL();
    private final ClientDAO clientDAO;

    /**
     * Returns instance of ProductBLL object
     *
     * @return
     */
    public static ClientBLL getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public ClientBLL() {
        this.clientDAO = ClientDAO.getInstance();
    }
    /**
     * Utilizes the method from TableUti to create a JTable of client objects and insert it into the interface
     *
     * @param frame
     * @throws SQLException
     */
    public void viewAllClients(JFrame frame) throws SQLException {

        ArrayList<Client> clientsList = clientDAO.getClientsList();
        JTable clientTable = TableUtil.createTable(clientsList);
        frame.add(new JScrollPane(clientTable));
    }

    /**
     * Inserts client into database with validators
     *
     * @param client_id
     * @param client_name
     * @param email
     * @param age
     * @param address
     * @throws SQLException
     */
    public void insertClient(int client_id, String client_name, String email, int age, String address) throws SQLException {
        if (ClientValidator.validateClient(client_name, email)) {
            clientDAO.insertClient(client_id, client_name, email, age, address);
            JOptionPane.showMessageDialog(null, "Successfully added!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid name or email format.");
        }
    }

    /**
     * Deletes client from database with validators
     *
     * @param client_id
     * @throws SQLException
     */
    public void deleteClient(int client_id) throws SQLException {
        if (!clientDAO.existsClientID(client_id)) {
            JOptionPane.showMessageDialog(null, "ID does not exist");
            return;
        }
        if (clientDAO.existsClientOrder(client_id)) {
            JOptionPane.showMessageDialog(null, "Can't delete client with an existing order!");
        } else {
           clientDAO.deleteClient(client_id);
            JOptionPane.showMessageDialog(null, "Successfully deleted!");
        }
    }

    /**
     * Edits client from database with validators
     *
     * @param client_id
     * @param client_name
     * @param email
     * @param age
     * @param address
     * @throws SQLException
     */
    public void editClient(int client_id, String client_name, String email, int age, String address) throws SQLException {
        if (!clientDAO.existsClientID(client_id)) {
            JOptionPane.showMessageDialog(null, "ID does not exist");
            return;
        }

        if (ClientValidator.validateClient(client_name, email)) {
            clientDAO.editClient(client_id, client_name, email, age, address);
            JOptionPane.showMessageDialog(null, "Successfully edited!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid name or email format. ");
        }
    }


}
