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

public class DeleteClientWindow extends JFrame{
    private JPanel deleteClientPanel;
    private JTextField clientIdField;
    private JButton deleteButton;

    public DeleteClientWindow()  {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(deleteClientPanel);
        frame.setSize(300, 200);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientBLL.getInstance().deleteClient(Integer.parseInt(clientIdField.getText()));
                    //ClientDAO.deleteClient(conn, Integer.parseInt(clientIdField.getText()));
                } catch (SQLException ex) {
                    //JOptionPane.showMessageDialog(null, "Client doesn't exist");
                }
                clientIdField.setText(null);
            }
        });
    }
}
