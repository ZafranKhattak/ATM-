package Dashboard;

import javax.swing.*;
import Auth.SignIn;
import java.awt.*;
import java.awt.event.*;

public class Dashboard {

    // String fullName;
    public Dashboard(String fullName) {        
           
        // =================== DISPLAY NAME ====================== //
        JLabel label = new JLabel();
        label.setLayout(null);
        label.setBounds(530 , 40,250,30);
        label.setText(fullName);
        label.setFont(new Font("Arail" , Font.BOLD , 18));
        label.addMouseListener(new MouseAdapter(){

            public void mouseEntered(MouseEvent e)
            {
                label.setForeground(Color.WHITE);
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e)
            {
                label.setForeground(Color.BLACK);
            }
        });
        

        JFrame frame = new JFrame("DASHBOARD");
        frame.getContentPane().setBackground(new Color(91, 44, 111));
        frame.setVisible(true);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(label);
        // ================== JPANEL ==================== //
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(450, 100, 400, 500);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        // ===================== WITHDRAW CASH BUTTON ============== //
        JButton wB = button("WithDraw Cash", 50);
        wB.addActionListener(e->{
            new WithdrawCash();
            frame.dispose();
        });
        panel.add(wB);
        // ===================== ADD CASH BUTTON ============== //
        JButton aB = button("ADD Cash", 120);
        aB.addActionListener(e->{
            new AddCash();
            frame.dispose();
        });
        panel.add(aB);
        // ===================== CHECK CASH BUTTON ============== //
        JButton cB = button("Check Cash", 200);
        cB.addActionListener(e->{
            new CheckCash();
            frame.dispose();
        });
        panel.add(cB);
        // ===================== SEND CASH BUTTON ============== //
        JButton sB = button("Transfer Cash", 280);
        sB.addActionListener(e->{
            new TransferCash();
            frame.dispose();
        });
        panel.add(sB);
        // ===================== BACK  BUTTON ============== //
        JButton bB = button("Back", 360);
        bB.setBackground(Color.BLUE);
        bB.addActionListener(e->{

            new SignIn();
            frame.dispose();
        });
        panel.add(bB);


    }

    // ========================= JBUTTON LOGIC METHOD ========================//
    JButton button(String text , int y)
    {
        JButton btn = new JButton(text);
        btn.setLayout(null);
        btn.setBounds(100 , y , 200 , 30 );
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial" , Font.BOLD , 17));

        return btn;
    }
}
