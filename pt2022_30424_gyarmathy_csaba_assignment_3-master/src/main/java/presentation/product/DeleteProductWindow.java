package presentation.product;

import bll.ProductBLL;
import connection.DatabaseConnection;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteProductWindow extends JFrame {

    private JTextField productIdField;
    private JPanel deleteProductPanel;
    private JButton deleteButton;

    public DeleteProductWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(deleteProductPanel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductBLL.getInstance().deleteProduct(Integer.parseInt(productIdField.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                productIdField.setText(null);
            }
        });
    }
}
