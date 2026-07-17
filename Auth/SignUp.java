package Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Database.DBConnection;
import java.sql.*;

public class SignUp {

    String fullName;
    public SignUp() {
        // =============== WINDOW JFRAME =================== //

        JFrame frame = new JFrame("SignUp Section");
        frame.getContentPane().setBackground(new Color(91, 44, 111));
        frame.setVisible(true);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================================================ //

        // ==================== JPANEL SECTION ======================= //

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(450, 70, 350, 600);
        panel.setBackground(new Color(167, 167, 229));
        frame.add(panel);

        // ================================================ //

        // ====================== JLabel SECTION ===================== //
        JLabel welcomeLabel = label("Welcome To Zafran's Bank",
                510, 30);

        frame.add(welcomeLabel);
        // ================================================ //

        // ===================== FIRST NAME LABEL =================== //
        JLabel firstLabel = label("First Name:", 40, 10);
        panel.add(firstLabel);
        // ========================= JTEXTFILED SECTION ========================== //
        JTextField firstField = field(40, 30);
        panel.add(firstField);

        // ===================== LAST NAME LABEL =================== //
        JLabel lastLabel = label("Last Name:", 40, 70);
        panel.add(lastLabel);

        // ===================== LAST NAME TEXTFIELD =================== //
        JTextField lastField = field(40, 90);
        panel.add(lastField);

        // ==================== MOBILE LABEL ===================== //
        JLabel cnicLabel = label("Mobile NO:", 40, 130);
        panel.add(cnicLabel);

        // ==================== MOBILE NO TEXTFILED =================== //
        JTextField cnicField = field(40, 150);
        panel.add(cnicField);

        // ==================== PIN OF ATM =====================//
        JLabel pinLabel = label("SET PIN", 40, 190);
        panel.add(pinLabel);

        // ==================== PIN TEXTFIELD ATM ===================== //
        JTextField pinField = field(40, 210);
        panel.add(pinField);

        // ==================== Email Label ============================ //
        JLabel emailLabel = label("Email", 40, 250);
        panel.add(emailLabel);

        // ==================== Email TextField =======================//
        JTextField emailField = field(40, 270);
        panel.add(emailField);

        // ===================== PASSWORD LABEL ==================== //
        JLabel passLabel = label("Enter Pass:", 40, 310);
        panel.add(passLabel);

        // ===================== PASSWORD FIELD =================== //
        JPasswordField pass = pass(40, 330);
        panel.add(pass);

        JLabel confirmLabel = label("Confirm Pass:", 40, 360);
        panel.add(confirmLabel);
        // ===================== CONFIRM PASSWORD =================== //
        JPasswordField confirPasswordField = pass(40, 380);
        panel.add(confirPasswordField);

        // ===================== BUTTON FIELD ====================== //
        JButton btn = new JButton();
        btn.setLayout(null);
        btn.setBounds(30, 430, 290, 40);
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
            if (cnic.length() != 11) {
                JOptionPane.showMessageDialog(null, "phone must be 11 characters");
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

            Connection con;
            PreparedStatement checkStmt;
            PreparedStatement ps;
            ResultSet rs;
            try {
                con = DBConnection.getConnection();
                String checkQuery = "SELECT email FROM USERS WHERE email = ? and mobile = ?";
                checkStmt = con.prepareStatement(checkQuery);
                checkStmt.setString(1, email);
                checkStmt.setString(2, cnic);
                rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Email or phone is already registered!");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Email or phone is already registered!");
            }
            try {
                con = DBConnection.getConnection();
                String query = "INSERT INTO USERS(first_name, last_name, pin, mobile, email, password) VALUES (?, ?, ?, ?, ?, ?)";
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
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        });

         // ===================== LOGIN BUTTON ====================== //
        JButton loginButton = new JButton();
        loginButton.setLayout(null);
        loginButton.setText("Log In");
        loginButton.setBounds(30, 500, 290, 40);
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(e->{
            new SignIn();
            frame.dispose();
        });
        panel.add(btn);
        panel.add(loginButton);
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
                label1.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                label1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                label1.setForeground(Color.black);
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