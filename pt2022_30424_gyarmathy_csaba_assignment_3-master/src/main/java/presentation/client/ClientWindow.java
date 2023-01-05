package presentation.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Main client window class generated by the Swing form editor
 * Contains ActionListeners for each button in the window
 *
 * @author Csabi
 */
public class ClientWindow extends JFrame {
    private JButton addNewClientButton;
    private JButton editExistingClientButton;
    private JButton deleteClientButton;
    private JButton viewAllClientsButton;
    private JPanel clientPanel;

    public ClientWindow() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.add(clientPanel);
        frame.setSize(300, 200);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        clientPanel.setLayout(null);

        addNewClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClientWindow();
            }
        });
        editExistingClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditClientWindow();
            }
        });
        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteClientWindow();
            }
        });
        viewAllClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ShowClientsWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
