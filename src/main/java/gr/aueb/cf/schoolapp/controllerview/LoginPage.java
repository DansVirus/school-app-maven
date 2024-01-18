package gr.aueb.cf.schoolapp.controllerview;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;


    /**
     * Create the frame.
     */
    public LoginPage() {
        setTitle("Είσοδος Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 541, 347);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(new Color(0, 0, 255));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUsername.setBounds(75, 57, 105, 35);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtUsername.setBounds(185, 53, 183, 43);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(new Color(0, 0, 255));
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassword.setBounds(75, 116, 105, 35);
        contentPane.add(lblPassword);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 183, 507, 2);
        contentPane.add(separator);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(new Color(0, 0, 255));
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
                String password = System.getenv("TS_ADMIN_PASSWORD");
                String hashedPassword;

                if (inputUsername.equals("") || inputPassword.equals("")) {
                    return;
                }

                if (inputUsername.equals("admin") && (inputPassword.equals(password))) {
                    Main.getLoginPage().setVisible(false);
                    Main.getSearchUser().setVisible(true);
                } else {
                    String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";

                    try (Connection conn = DBUtil.getConnection();
                         PreparedStatement p = conn.prepareStatement(sql)) {

                        p.setString(1, inputUsername);
                        ResultSet rs = p.executeQuery();

                        if (rs.next()) {
                            hashedPassword = rs.getString("PASSWORD");
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (BCrypt.checkpw(inputPassword, hashedPassword)) {
                            Main.getMenu().setVisible(true);
                            Main.getLoginPage().setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error in password", "Login error", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnLogin.setBounds(255, 217, 113, 54);
        contentPane.add(btnLogin);

        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("");
        txtPassword.setBounds(185, 112, 183, 43);
        contentPane.add(txtPassword);
    }
}
