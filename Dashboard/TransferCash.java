package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransferCash {

    public TransferCash() {
        JFrame frame = new JFrame("Transfer Cash");
        frame.getContentPane().setBackground(new Color(91, 44, 111));
        frame.setVisible(true);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ==================== PANEL ==========================//
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(500, 100, 300, 450);
        frame.add(panel);

        // ================== MOBILE FILED ========================== //

        JTextField mobileField = field("Mobile No: ", 40);
        panel.add(mobileField);

        // ================== AMOUNT TRANSFER FILED ==================== //
        JTextField amountField = field("Amount :", 120);
        panel.add(amountField);

        // ================== Tranfer Button ===================== //
        JButton transferButton = button("Send", 200 , Color.BLUE , Color.WHITE);
        panel.add(transferButton);

        // ================== Back Button ===================== //
        JButton backButton = button("Back", 270 , Color.RED , Color.WHITE);
        backButton.addActionListener(e->{
            // new Dashboard();
            frame.dispose();
        });
        panel.add(backButton);
    }

    JTextField field(String text, int y) {
        JTextField fld = new JTextField(text);
        fld.setLayout(null);
        fld.setBounds(40, y, 220, 40);
        fld.setBackground(Color.DARK_GRAY);
        fld.setForeground(Color.WHITE);
        fld.setFont(new Font("Arail", Font.BOLD, 17));
        fld.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                if (fld.getText().equals(text)) {
                    fld.setText("");
                }
            }

            public void mouseExited(MouseEvent e) {
                if (fld.getText().isEmpty()) {
                    fld.setText(text);
                }
            }
        });

        return fld;
    }

    // ======================== BACK BUTTON METHOD LOGIC =======================//
    
    JButton button(String text , int y , Color trans , Color back)
    {
        JButton btn = new JButton(text);
        btn.setLayout(null);
        btn.setBounds(40 , y , 220 , 50);
        btn.setBackground(trans);
        btn.setForeground(back);
        btn.setFont(new Font("Arial" , Font.BOLD , 18));

        return btn;
    }
    

}
