package presentation.product;

import bll.ProductBLL;
import connection.DatabaseConnection;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddProductWindow extends JFrame {
    private JPanel addProductPanel;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productStockField;
    private JButton addProdcutButton;
    private JTextField productIdField;

    public AddProductWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(addProductPanel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addProdcutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductBLL.getInstance().insertProduct(
                            Integer.parseInt(productIdField.getText()),
                            productNameField.getText(),
                            Integer.parseInt(productPriceField.getText()),
                            Integer.parseInt(productStockField.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    //JOptionPane.showMessageDialog(null, "Wrong input format.");
                }
                productIdField.setText(null);
                productNameField.setText(null);
                productPriceField.setText(null);
                productStockField.setText(null);
            }
        });
    }
}
