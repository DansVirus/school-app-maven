package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertUser extends JFrame {
    private static final long serialVersionUID = 1L;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private PreparedStatement p;


    /**
     * Create the frame.
     */
    public InsertUser() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
        setTitle("Εισαγωγή Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(new Color(153, 0, 51));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsername.setBounds(65, 44, 85, 26);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(160, 48, 188, 19);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        txtPassword = new JTextField();
        txtPassword.setBounds(160, 86, 188, 19);
        contentPane.add(txtPassword);
        txtPassword.setColumns(50);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setForeground(new Color(153, 0, 51));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPassword.setBounds(65, 80, 85, 26);
        contentPane.add(lblPassword);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String inputUsername = txtUsername.getText().trim();
                String inputPassword = txtPassword.getText().trim();

                if (inputUsername.equals("") || (inputPassword.equals(""))) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUsername(inputUsername);
                    userDTO.setPassword(inputPassword);

                    User user = userService.insertUser(userDTO);

                    JOptionPane.showMessageDialog(null, "User " + user.getPassword() + " was inserted",
                            "INSERT", JOptionPane.PLAIN_MESSAGE);
                } catch (UserDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btnInsert.setForeground(new Color(0, 0, 153));
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(201, 210, 85, 30);
        contentPane.add(btnInsert);

        JSeparator separator = new JSeparator();
        separator.setBounds(21, 172, 389, 2);
        contentPane.add(separator);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getInsertUser().setVisible(false);
                Main.getSearchUser().setEnabled(true);

            }
        });
        btnClose.setForeground(new Color(0, 0, 153));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(307, 210, 85, 30);
        contentPane.add(btnClose);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(46, 26, 327, 109);
        contentPane.add(panel);
    }
}