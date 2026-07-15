package Auth;

import javax.swing.*;

import Database.DBConnection;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignIn {
    public SignIn() {

        JFrame frame = new JFrame("Enter PIN");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(91, 44, 111));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);

        // ==================== JPANEL ====================== //
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(450, 200, 400, 450);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        frame.add(panel);

        // ==================== logo image ====================//
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("Auth/BANK LOGO.png");
        logo.setIcon(
                new ImageIcon(
                        icon.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH)));
        logo.setLayout(null);
        logo.setBounds(530 , 0,300,200);
        frame.add(logo);
        // =================== SIGN IN SECTION ================//
        JLabel label = new JLabel("<HTML> Welcome To <br>Zafran Bank</b> </HTML");
        label.setLayout(null);
        label.setBounds(150, 0, 150, 130);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(label);
        // ================= CNIC FIELD =======================//
        JTextField cnicField = field("Enter CNIC", 75, 100);
        panel.add(cnicField);

        // ================= PIN FIELD =================== //
        JTextField pinField = field("Enter PIN", 75, 150);
        panel.add(pinField);

        // =================== BUTTON =================== //
        JButton button = new JButton("Enter");
        button.setLayout(null);
        button.setBounds(25, 250, 350, 40);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(e -> {

            String cnic = cnicField.getText().trim();
            String pin = pinField.getText().trim();

            try {

                Connection con = DBConnection.getConnection();
                String query = "SELECT cnic , pin from USERS WHERE cnic = ? and pin = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, cnic);
                ps.setString(2, pin);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successfully");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Issue In Login");
                }

                con.close();
                ps.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });
        panel.add(button);

        // ======================= DONOT HAVE ACCOUNT //
        JLabel label2 = new JLabel();
        label2.setText("<html> <u>Don't have account? SignUp</u></html>");
        label2.setForeground(Color.WHITE);
        label2.setLayout(null);
        label2.setBounds(80, 300, 250, 30);
        label2.setFont(new Font("Arial", Font.BOLD, 18));
        label2.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
                frame.dispose();
            }
        });
        panel.add(label2);

    }

    // ======================== METHOD TEXTFIELD LOGIC ==================== //
    JTextField field(String text, int x, int y) {
        JTextField fld = new JTextField();
        fld.setText(text);
        fld.setLayout(null);
        fld.setBounds(x, y, 250, 30);
        fld.setBackground(Color.WHITE);
        fld.setForeground(Color.DARK_GRAY);
        fld.setFont(new Font("Arial", Font.PLAIN, 15));
        fld.setBorder(BorderFactory.createLineBorder(Color.red, 3));
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
}
