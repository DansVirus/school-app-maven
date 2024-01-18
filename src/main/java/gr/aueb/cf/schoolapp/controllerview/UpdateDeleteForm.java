package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;


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

public class  UpdateDeleteForm extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtLastname;
    private JTextField txtFirstname;
    private JTextField txtID;

    private ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    private int listPosition;
    private int listSize;
    List<Teacher> teachers;

    /**
     * Create the frame.
     */
    public UpdateDeleteForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {

                try {
                    teachers = teacherService.getTeacherByLastname(Main.getSearchForm().getInputLastname());

                    listPosition = 0;
                    listSize = teachers.size();

                    if (listSize == 0) {
                        txtID.setText("");
                        txtFirstname.setText("");
                        txtLastname.setText("");
                        return;
                    }

                    txtID.setText(Integer.toString(teachers.get(listPosition).getId()));
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                } catch (TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting teachers", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
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
                        txtID.setText(String.format("%s", teachers.get(listPosition).getId()));
                        txtLastname.setText(teachers.get(listPosition).getLastname());
                        txtFirstname.setText(teachers.get(listPosition).getFirstname());
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
                    txtID.setText(String.format("%s", teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
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
                    txtID.setText(String.format("%s", teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
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
                    txtID.setText(String.format("%s", teachers.get(listPosition).getId()));
                    txtLastname.setText(teachers.get(listPosition).getLastname());
                    txtFirstname.setText(teachers.get(listPosition).getFirstname());
                }

            }
        });
        btnLast_Record.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        btnLast_Record.setBounds(257, 217, 66, 40);
        contentPane.add(btnLast_Record);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLastname.setBounds(15, 75, 85, 32);
        contentPane.add(lblLastname);

        txtLastname = new JTextField();
        txtLastname.setColumns(10);
        txtLastname.setBounds(130, 72, 255, 35);
        contentPane.add(txtLastname);

        JLabel lblFirstname = new JLabel("Όνομα");
        lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblFirstname.setBounds(15, 120, 85, 32);
        contentPane.add(lblFirstname);

        txtFirstname = new JTextField();
        txtFirstname.setColumns(10);
        txtFirstname.setBounds(130, 117, 255, 35);
        contentPane.add(txtFirstname);

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
                        teacherService.deleteTeacher(id);
                        JOptionPane.showMessageDialog(null, "Teacher was deleted successfully",
                                "Delete", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (TeacherDAOException | TeacherNotFoundException e1) {
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
                String inputLastname = txtLastname.getText().trim();
                String inputFirstname = txtFirstname.getText().trim();

                if (inputLastname.equals("") || (inputFirstname.equals("")) || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid update", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setId(Integer.parseInt(id));
                    teacherDTO.setFirstname(inputFirstname);
                    teacherDTO.setLastname(inputLastname);

                    Teacher teacher = teacherService.updateTeacher(teacherDTO);

                    JOptionPane.showMessageDialog(null,  "Teacher" + " was updated",
                            "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch (TeacherDAOException | TeacherNotFoundException e1) {
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
                Main.getSearchForm().setEnabled(true);
                Main.getUpdateDeleteForm().setVisible(false);
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnClose.setBounds(281, 326, 116, 49);
        contentPane.add(btnClose);
    }

}
