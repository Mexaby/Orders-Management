package presentation.order;

import bll.OrderBLL;

import javax.swing.*;
import java.sql.SQLException;

public class ShowOrdersWindow extends JFrame{
    JFrame frame = new JFrame();
    private JPanel showOrdersPanel;

    public ShowOrdersWindow() throws SQLException {
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(showOrdersPanel);
        setState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        OrderBLL.getInstance().viewAllOrders(frame);
    }
}
