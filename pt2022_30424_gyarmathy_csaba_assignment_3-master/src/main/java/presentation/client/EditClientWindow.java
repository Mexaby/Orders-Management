package presentation.client;

import bll.ClientBLL;
import connection.DatabaseConnection;
import dao.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EditClientWindow extends JFrame{
    Connection conn = DatabaseConnection.connect();
    private JPanel editClientPanel;
    private JTextField clientIdField;
    private JTextField clientNameField;
    private JTextField clientAddressField;
    private JTextField clientAgeField;
    private JTextField clientEmailField;
    private JButton editButton;

    public EditClientWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(editClientPanel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientBLL.getInstance().editClient(
                            Integer.parseInt(clientIdField.getText()),
                            clientNameField.getText(),
                            clientEmailField.getText(),
                            Integer.parseInt(clientAgeField.getText()),
                            clientAddressField.getText());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });
    }
}
