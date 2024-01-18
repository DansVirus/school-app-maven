package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class UpdateDeleteUser extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPassword;
    private JTextField txtUsername;
    private JTextField txtID;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    private int listPosition;
    private int listSize;
    List<User> users;

    /**
     * Create the frame.
     */
    public UpdateDeleteUser() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {

                try {
                    users = userService.getUserByUsername(Main.getSearchUser().getInputUsername());

                    listPosition = 0;
                    listSize = users.size();

                    if (listSize == 0) {
                        txtID.setText("");
                        txtUsername.setText("");
                        txtPassword.setText("");
                        return;
                    }

                    txtID.setText(Integer.toString(users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                } catch (UserDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting Users", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        setTitle("Ενημέρωση / Διαγραφή Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 628, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnFirst_Record = new JButton("");
        btnFirst_Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    txtID.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }
            }
        });
        btnFirst_Record.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
        btnFirst_Record.setBounds(29, 217, 66, 40);
        contentPane.add(btnFirst_Record);

        JButton btnPrevious_Record = new JButton("");
        btnPrevious_Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;
                    txtID.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }

            }
        });
        btnPrevious_Record.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
        btnPrevious_Record.setBounds(105, 217, 66, 40);
        contentPane.add(btnPrevious_Record);

        JButton btnNext_Record = new JButton("");
        btnNext_Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    txtID.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());

                }
            }
        });
        btnNext_Record.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
        btnNext_Record.setBounds(181, 217, 66, 40);
        contentPane.add(btnNext_Record);

        JButton btnLast_Record = new JButton("");
        btnLast_Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    txtID.setText(String.format("%s", users.get(listPosition).getId()));
                    txtUsername.setText(users.get(listPosition).getUsername());
                    txtPassword.setText(users.get(listPosition).getPassword());
                }

            }
        });
        btnLast_Record.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        btnLast_Record.setBounds(257, 217, 66, 40);
        contentPane.add(btnLast_Record);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(15, 75, 85, 32);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(130, 77, 255, 35);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(15, 120, 85, 32);
        contentPane.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(130, 122, 255, 35);
        contentPane.add(txtPassword);

        JLabel lblID = new JLabel("Κωδικός");
        lblID.setForeground(new Color(204, 0, 51));
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblID.setBounds(15, 29, 73, 32);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBackground(new Color(255, 255, 204));
        txtID.setEditable(false);
        txtID.setColumns(10);
        txtID.setBounds(130, 26, 117, 35);
        contentPane.add(txtID);

        JSeparator separator = new JSeparator();
        separator.setBounds(15, 199, 589, 2);
        contentPane.add(separator);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    int response;
                    int id;
                    String idStr = txtID.getText();

                    if (idStr.equals("")) return;
                    id = Integer.parseInt(txtID.getText());

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        userService.deleteUser(id);
                        JOptionPane.showMessageDialog(null, "User was deleted successfully",
                                "Delete", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null,   message,
                            "Delete", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnDelete.setBounds(29, 326, 116, 49);
        contentPane.add(btnDelete);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                String id = txtID.getText().trim();
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = txtPassword.getText().trim();


                if (inputPassword.equals("") || (inputUsername.equals("")) || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid update", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(Integer.parseInt(id));
                    userDTO.setUsername(inputUsername);
                    userDTO.setPassword(inputPassword);

                    User user = userService.updateUser(userDTO);

                    JOptionPane.showMessageDialog(null,  "User " + " was updated",
                            "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnUpdate.setBounds(155, 326, 116, 49);
        contentPane.add(btnUpdate);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(true);
                Main.getUpdateDeleteUser().setVisible(false);
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnClose.setBounds(281, 326, 116, 49);
        contentPane.add(btnClose);
    }

}
