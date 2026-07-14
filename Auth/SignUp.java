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
        JLabel firstLabel = label("First Name:", 20, 10);
        panel.add(firstLabel);
        // ========================= JTEXTFILED SECTION ========================== //
        JTextField firstField = field( 20, 30);
        panel.add(firstField);

        // ===================== LAST NAME LABEL =================== //
        JLabel lastLabel = label("Last Name:", 20, 70);
        panel.add(lastLabel);

        // ===================== LAST NAME TEXTFIELD =================== //
        JTextField lastField = field(20, 90);
        panel.add(lastField);

        // ==================== CNIC LABEL ===================== //
        JLabel cnicLabel = label("Enter CNIC", 20, 130);
        panel.add(cnicLabel);

        // ==================== CNIC TEXTFILED =================== //
        JTextField cnicField = field(20, 150);
        panel.add(cnicField);

        // ==================== PIN OF ATM =====================//
        JLabel pinLabel = label("SET PIN", 20, 190);
        panel.add(pinLabel);

        // ==================== PIN TEXTFIELD ATM ===================== //
        JTextField pinField = field(20, 210);
        panel.add(pinField);

        // ==================== Email Label ============================ //
        JLabel emailLabel = label("Email", 20, 250);
        panel.add(emailLabel);

        // ==================== Email TextField =======================//
        JTextField emailField = field( 20, 270);
        emailField.setBounds(20, 270, 200, 30);
        panel.add(emailField);

        // ===================== PASSWORD LABEL ==================== //
        JLabel passLabel = label("Enter Pass:", 20, 310);
        panel.add(passLabel);

        // ===================== PASSWORD FIELD =================== //
        JPasswordField pass = pass(20, 330);
        panel.add(pass);

        JLabel confirmLabel = label("Confirm Pass:", 20, 360);
        panel.add(confirmLabel);
        // ===================== CONFIRM PASSWORD =================== //
        JPasswordField confirPasswordField = pass(20, 380);
        panel.add(confirPasswordField);

        // ===================== BUTTON FIELD ====================== //
        JButton btn = new JButton();
        btn.setLayout(null);
        btn.setBounds(80, 420, 200, 40);
        btn.setText("Save");
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        btn.addActionListener(e -> {

            String firstName = firstField.getText().trim();

            if (firstName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter First Name");
                return;
            }
            String lastName = lastField.getText().trim();
            if (lastName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Last Name");
                return;
            }
            String cnic = cnicField.getText().trim();
            if (cnic.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter CNIC");
                return;
            }
            if(cnic.length() <13 || cnic.length()>13)
            {
                JOptionPane.showMessageDialog(null, "CNIC IS INVALID MUST BE OF 13 CHARACTER");
                return;
            }
            String pin = pinField.getText();
            if (pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PIN");
                return;
            }
            if (pin.length()<4 || pin.length()>4)
            {
                JOptionPane.showMessageDialog(null, "Pin Should be of four digit");
                return;
            }
            String email = emailField.getText();
            if (email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Email");
                return;
            }
            if (!email.contains("@gmail.com"))
            {
                JOptionPane.showMessageDialog(null , "invalid email formate");
                return;
            }
            String password = String.valueOf(pass.getPassword());
            if (password.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter your Password");
                return;
            }
            String confirmPassword = String.valueOf(confirPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "password does not match");
                return;
            }

            try {
                System.out.println("Database");
                Connection con = DBConnection.getConnection();
                String query = "INSERT INTO USERS(first_name , last_name , pin , cnic, email, password) VALUES ( ?, ? , ? ,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, pin);
                ps.setString(4, cnic);
                ps.setString(5, email);
                ps.setString(6, password);
                int row = ps.executeUpdate();

                if (row > 0) {
                    System.out.println(row);
                    JOptionPane.showMessageDialog(null, "Account Created Succefully");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Create Account");
                }

                ps.close();

            } catch (Exception e1) {
                e1.printStackTrace();
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
        fld.setBounds(x, y, 180, 30);
        fld.setBackground(Color.BLACK);
        fld.setForeground(Color.LIGHT_GRAY);
        fld.setFont(new Font("Arial", Font.BOLD, 15));

        return fld;
    }

    // =========================== PASSWORD METHOD ====================== //
    JPasswordField pass(int x, int y) {
        JPasswordField pass = new JPasswordField();
        pass.setLayout(null);
        pass.setBounds(x, y, 180, 30);
        pass.setBackground(Color.BLACK);
        pass.setForeground(Color.LIGHT_GRAY);
        pass.setFont(new Font("Arial", Font.BOLD, 15));
        return pass;
    }
}