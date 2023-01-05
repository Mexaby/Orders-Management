package presentation.product;

import bll.ProductBLL;
import connection.DatabaseConnection;
import dao.ClientDAO;
import dao.ProductDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowProductsWindow extends JFrame {
    JFrame frame = new JFrame();
    private JPanel showProductsPanel;

    public ShowProductsWindow() throws SQLException {
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(showProductsPanel);
        setState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ProductBLL.getInstance().viewAllProducts(frame);
    }
}
