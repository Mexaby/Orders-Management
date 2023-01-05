package presentation.client;

import bll.ClientBLL;

import javax.swing.*;
import java.sql.SQLException;

public class ShowClientsWindow extends JFrame{
    private JPanel showClientsPanel;

    public ShowClientsWindow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.add(showClientsPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);

        setExtendedState(MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);


        ClientBLL.getInstance().viewAllClients(frame);
    }
}
