package presentation.order;

import bll.OrderBLL;
import connection.DatabaseConnection;
import dao.OrderDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteOrderWindow extends JFrame {
    private JTextField deleteField;
    private JPanel deleteOrderPanel;
    private JButton deleteButton;

    public DeleteOrderWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(deleteOrderPanel);
        frame.pack();
        //frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderBLL.getInstance().deleteOrder(Integer.parseInt(deleteField.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                deleteField.setText(null);
            }
        });
    }
}
