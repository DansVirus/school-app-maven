package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {


    private static final long serialVersionUID = 1L;
    private JPanel contentPane;



    public Menu() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblQuality2 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblQuality2.setBounds(242, 22, 252, 31);
        getContentPane().add(lblQuality2);

        JSeparator separator = new JSeparator();
        separator.setBounds(32, 111, 678, 2);
        getContentPane().add(separator);

        JButton btnTeachers = new JButton("");
        btnTeachers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchForm().setVisible(true);
            }
        });
        btnTeachers.setBounds(49, 136, 60, 60);
        getContentPane().add(btnTeachers);

        JButton btnStudents = new JButton("");
        btnStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
               // Main.getSearchFormStudents().setVisible(true);
            }
        });
        btnStudents.setBounds(49, 217, 60, 60);
        getContentPane().add(btnStudents);

        JLabel lblTeachers = new JLabel("Εκπαιδευτές");
        lblTeachers.setForeground(new Color(153, 51, 51));
        lblTeachers.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTeachers.setBounds(119, 152, 142, 31);
        getContentPane().add(lblTeachers);

        JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
        lblStudents.setForeground(new Color(153, 51, 51));
        lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblStudents.setBounds(119, 232, 142, 31);
        getContentPane().add(lblStudents);

        JLabel lblQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality1.setForeground(new Color(0, 128, 0));
        lblQuality1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblQuality1.setBounds(244, 24, 252, 31);
        getContentPane().add(lblQuality1);


    }

}
