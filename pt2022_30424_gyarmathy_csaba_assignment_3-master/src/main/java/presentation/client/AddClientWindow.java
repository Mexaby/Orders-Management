package presentation.client;

import bll.ClientBLL;
import connection.DatabaseConnection;
import dao.ClientDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddClientWindow extends JFrame {
    private JPanel addClientPanel;
    private JTextField clientIdField;
    private JTextField clientNameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton addClientButton;

    public AddClientWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(addClientPanel);
        //frame.pack();
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientBLL.getInstance().insertClient(
                            Integer.parseInt(clientIdField.getText()),
                            clientNameField.getText(),
                            emailField.getText(),
                            Integer.parseInt(ageField.getText()),
                            addressField.getText());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }

                clientIdField.setText(null);
                clientNameField.setText(null);
                emailField.setText(null);
                ageField.setText(null);
                addressField.setText(null);
            }
        });
    }

    public String getClientIdField() {
        return clientIdField.getText();
    }

    public String getClientNameField() {
        return clientNameField.getText();
    }

    public String getAgeField() {
        return ageField.getText();
    }

    public String getAddressField() {
        return addressField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }
}
