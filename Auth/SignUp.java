package Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Database.DBConnection;
import java.sql.*;

public class SignUp {

    public SignUp() {

        // =============== WINDOW JFRAME =================== //

        JFrame frame = new JFrame("SignUp Section");
        frame.setVisible(true);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(89, 65, 65));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================================================ //

        // ==================== JPANEL SECTION ======================= //

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(450, 100, 400, 500);
        panel.setBackground(new Color(167, 167, 229));
        frame.add(panel);

        // ================================================ //

        // ====================== JLabel SECTION ===================== //
        JLabel welcomeLabel = label("SignUp Section",
                570, 70);

        frame.add(welcomeLabel);
        // ================================================ //

        // ===================== FIRST NAME LABEL =================== //
        JLabel firstLabel = label("First Name:", 70, 10);
        panel.add(firstLabel);
        // ========================= JTEXTFILED SECTION ========================== //
        JTextField firstField = field(70, 30);
        panel.add(firstField);

        // ===================== LAST NAME LABEL =================== //
        JLabel lastLabel = label("Last Name:", 70, 70);
        panel.add(lastLabel);

        // ===================== LAST NAME TEXTFIELD =================== //
        JTextField lastField = field(70, 90);
        panel.add(lastField);

        // ==================== CNIC LABEL ===================== //
        JLabel cnicLabel = label("Enter CNIC", 70, 130);
        panel.add(cnicLabel);

        // ==================== CNIC TEXTFILED =================== //
        JTextField cnicField = field(70, 150);
        panel.add(cnicField);

        // ==================== PIN OF ATM =====================//
        JLabel pinLabel = label("SET PIN", 70, 190);
        panel.add(pinLabel);

        // ==================== PIN TEXTFIELD ATM ===================== //
        JTextField pinField = field(70, 210);
        panel.add(pinField);

        // ==================== Email Label ============================ //
        JLabel emailLabel = label("Email", 70, 250);
        panel.add(emailLabel);

        // ==================== Email TextField =======================//
        JTextField emailField = field(70, 270);
        panel.add(emailField);

        // ===================== PASSWORD LABEL ==================== //
        JLabel passLabel = label("Enter Pass:", 70, 310);
        panel.add(passLabel);

        // ===================== PASSWORD FIELD =================== //
        JPasswordField pass = pass(70, 330);
        panel.add(pass);

        JLabel confirmLabel = label("Confirm Pass:", 70, 360);
        panel.add(confirmLabel);
        // ===================== CONFIRM PASSWORD =================== //
        JPasswordField confirPasswordField = pass(70, 380);
        panel.add(confirPasswordField);

        // ===================== BUTTON FIELD ====================== //
        JButton btn = new JButton();
        btn.setLayout(null);
        btn.setBounds(80, 450, 250, 40);
        btn.setText("Sign Up");
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        btn.addActionListener(e -> {
            String firstName = firstField.getText().trim();
            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter First Name");
                return;
            }

            String lastName = lastField.getText().trim();
            if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Last Name");
                return;
            }

            String cnic = cnicField.getText().trim();
            if (cnic.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter CNIC");
                return;
            }
            if (cnic.length() != 13) {
                JOptionPane.showMessageDialog(null, "CNIC must be 13 characters");
                return;
            }

            String pin = pinField.getText().trim();
            if (pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter PIN");
                return;
            }
            if (pin.length() != 4) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                return;
            }

            String email = emailField.getText().trim().toLowerCase();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Email");
                return;
            }
            if (!email.contains("@gmail.com")) {
                JOptionPane.showMessageDialog(null, "Invalid email format");
                return;
            }

            String password = String.valueOf(pass.getPassword());
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter your Password");
                return;
            }

            String confirmPassword = String.valueOf(confirPasswordField.getPassword());
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match");
                return;
            }

            Connection con = null;
            PreparedStatement checkStmt = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                con = DBConnection.getConnection();
                String checkQuery = "SELECT email FROM USERS WHERE email = ?";
                checkStmt = con.prepareStatement(checkQuery);
                checkStmt.setString(1, email);
                rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Email is already registered!");
                    return;
                }

                String query = "INSERT INTO USERS(first_name, last_name, pin, cnic, email, password) VALUES (?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(query);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, pin);
                ps.setString(4, cnic);
                ps.setString(5, email);
                ps.setString(6, password);

                int row = ps.executeUpdate();
                if (row > 0) {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Create Account");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Email already exists!");
            } 

        });
        panel.add(btn);
    }

    // ======================= LABEL METHOD ========================== //
    JLabel label(String text, int x, int y) {
        JLabel label1 = new JLabel();
        label1.setText(text);
        label1.setLayout(null);
        label1.setBounds(x, y, 250, 25);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Arial", Font.BOLD, 18));
        label1.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {

                label1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label1.setForeground(Color.PINK);
            }

            public void mouseExited(MouseEvent e) {
                label1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                label1.setForeground(Color.WHITE);
            }
        });
        return label1;
    }

    // ====================== TEXT FIELD METHOD =======================//

    JTextField field(int x, int y) {
        JTextField fld = new JTextField();

        fld.setLayout(null);
        fld.setBounds(x, y, 280, 30);
        fld.setBackground(Color.BLACK);
        fld.setForeground(Color.LIGHT_GRAY);
        fld.setFont(new Font("Arial", Font.BOLD, 15));

        return fld;
    }

    // =========================== PASSWORD METHOD ====================== //
    JPasswordField pass(int x, int y) {
        JPasswordField pass = new JPasswordField();
        pass.setLayout(null);
        pass.setBounds(x, y, 280, 30);
        pass.setBackground(Color.BLACK);
        pass.setForeground(Color.LIGHT_GRAY);
        pass.setFont(new Font("Arial", Font.BOLD, 15));
        return pass;
    }
}