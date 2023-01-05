package presentation.product;

import bll.ProductBLL;
import connection.DatabaseConnection;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EditProductWindow extends JFrame{
    private JPanel editProductPanel;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productStockField;
    private JButton editButton;

    public EditProductWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(editProductPanel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductBLL.getInstance().editProduct(
                            Integer.parseInt(productIdField.getText()),
                            productNameField.getText(),
                            Integer.parseInt(productPriceField.getText()),
                            Integer.parseInt(productStockField.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                productIdField.setText(null);
                productNameField.setText(null);
                productPriceField.setText(null);
                productStockField.setText(null);
            }
        });
    }
}
