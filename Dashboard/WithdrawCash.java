package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WithdrawCash {

    public WithdrawCash() {
        JFrame frame = new JFrame("WITHDRAW");
        frame.getContentPane().setBackground(new Color(91, 44, 111));
        frame.setVisible(true);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================= JPANEL ========================== //
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);
        panel.setBounds(500, 100, 300, 450);
        frame.add(panel);
        // ========================== JTextFiled ====================== //

        JTextField wField = new JTextField();
        wField.setText("Amount:");
        wField.setLayout(null);
        wField.setBounds(50, 100, 220, 30);
        wField.setBackground(Color.WHITE);
        wField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        wField.setFont(new Font("Arial", Font.BOLD, 18));
        wField.setForeground(Color.BLACK);
        wField.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                if (wField.getText().equals(("Amount:"))) {
                    wField.setText("");
                }
            }

            public void mouseExited(MouseEvent e) {
                if (wField.getText().isEmpty()) {
                    wField.setText(("Amount:"));
                }
            }
        });
        panel.add(wField);

        // ======================= BUTTON WITHDRAW ======================== //

        JButton transferButton = button("WithDraw", 200 , Color.BLUE , Color.WHITE);
        panel.add(transferButton);

        // ================== Back Button ===================== //
        JButton backButton = button("Back", 270 , Color.RED , Color.WHITE);
        backButton.addActionListener(e->{
            // new Dashboard();
            frame.dispose();
        });
        panel.add(backButton);
    }

    JButton button(String text, int y, Color trans, Color back) {
        JButton btn = new JButton(text);
        btn.setLayout(null);
        btn.setBounds(40, y, 220, 50);
        btn.setBackground(trans);
        btn.setForeground(back);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        return btn;
    }
}
