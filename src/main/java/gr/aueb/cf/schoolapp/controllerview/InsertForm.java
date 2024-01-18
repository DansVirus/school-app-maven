package gr.aueb.cf.schoolapp.controllerview;


import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

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

public class InsertForm extends JFrame {
        private static final long serialVersionUID = 1L;

        private ITeacherDAO teacherDAO = new TeacherDAOImpl();
        private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
        private JPanel contentPane;
        private JTextField txtLastname;
        private JTextField txtFirstname;
        private PreparedStatement p;


        /**
         * Create the frame.
         */
        public InsertForm() {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowActivated(WindowEvent e) {
                    txtLastname.setText("");
                    txtFirstname.setText("");
                }
            });
            setTitle("Εισαγωγή Εκπαιδευτή");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblLastname = new JLabel("Επώνυμο");
            lblLastname.setForeground(new Color(153, 0, 51));
            lblLastname.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblLastname.setBounds(78, 44, 72, 26);
            contentPane.add(lblLastname);

            txtLastname = new JTextField();
            txtLastname.setBounds(160, 48, 188, 19);
            contentPane.add(txtLastname);
            txtLastname.setColumns(50);

            txtFirstname = new JTextField();
            txtFirstname.setBounds(160, 86, 188, 19);
            contentPane.add(txtFirstname);
            txtFirstname.setColumns(50);

            JLabel lblFirstname = new JLabel("Όνομα");
            lblFirstname.setHorizontalAlignment(SwingConstants.CENTER);
            lblFirstname.setForeground(new Color(153, 0, 51));
            lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblFirstname.setBounds(78, 80, 72, 26);
            contentPane.add(lblFirstname);

            JButton btnInsert = new JButton("Insert");
            btnInsert.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                  String inputLastname = txtLastname.getText().trim();
                  String inputFirstname = txtFirstname.getText().trim();

                    if (inputLastname.equals("") || (inputFirstname.equals(""))) {
                        JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        TeacherDTO teacherDTO = new TeacherDTO();
                        teacherDTO.setFirstname(inputFirstname);
                        teacherDTO.setLastname(inputLastname);

                        Teacher teacher = teacherService.insertTeacher(teacherDTO);

                        JOptionPane.showMessageDialog(null,  "Teacher" + teacher.getLastname() + " was inserted",
                                "INSERT", JOptionPane.PLAIN_MESSAGE);
                    } catch (TeacherDAOException e1) {
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
                    Main.getSearchForm().setEnabled(true);
                    Main.getInsertForm().setVisible(false);
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

