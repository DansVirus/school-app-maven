package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.controllerview.*;

import java.awt.EventQueue;

public class Main {

    private static Menu menu;
    private static SearchForm searchForm;
//    private static SearchFormStudents searchFormStudents;
    private static InsertForm insertForm;
//    private static InsertFormStudents insertFormStudents;
    private static UpdateDeleteForm updateDeleteForm;
//    private static UpdateDeleteFormStudents updateDeleteFormStudents;
//
    private static LoginPage loginPage;
    private static InsertUser insertUser;
    private static SearchUser searchUser;
    private static UpdateDeleteUser updateDeleteUser;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu = new Menu();
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(false);

                    searchForm = new SearchForm();
                    searchForm.setLocationRelativeTo(null);
                    searchForm.setVisible(false);

                    insertForm = new InsertForm();
                    insertForm.setLocationRelativeTo(null);
                    insertForm.setVisible(false);

                    updateDeleteForm = new UpdateDeleteForm();
                    updateDeleteForm.setLocationRelativeTo(null);
                    updateDeleteForm.setVisible(false);

//                    searchFormStudents = new SearchFormStudents();
//                    searchFormStudents.setLocationRelativeTo(null);
//                    searchFormStudents.setVisible(false);
//
//                    insertFormStudents = new InsertFormStudents();
//                    insertFormStudents.setLocationRelativeTo(null);
//                    insertFormStudents.setVisible(false);
//
//                    updateDeleteFormStudents = new UpdateDeleteFormStudents();
//                    updateDeleteFormStudents.setLocationRelativeTo(null);
//                    updateDeleteFormStudents.setVisible(false);
//
                    loginPage = new LoginPage();
                    loginPage.setLocationRelativeTo(null);
                    loginPage.setVisible(true);

                    insertUser = new InsertUser();
                    insertUser.setLocationRelativeTo(null);
                    insertUser.setVisible(false);

                    searchUser = new SearchUser();
                    searchUser.setLocationRelativeTo(null);
                    searchUser.setVisible(false);

                    updateDeleteUser = new UpdateDeleteUser();
                    updateDeleteUser.setLocationRelativeTo(null);
                    updateDeleteUser.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Menu getMenu() {
        return menu;
    }

//    public static SearchFormTeachers getSearchFormTeachers() {
//        return searchFormTeachers;
//    }

    public static InsertForm getInsertForm() {
        return insertForm;
    }

    public static UpdateDeleteForm getUpdateDeleteForm() {
        return updateDeleteForm;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static InsertUser getInsertUser() {
        return insertUser;
    }

    public static SearchUser getSearchUser() {
        return searchUser;
    }

    public static UpdateDeleteUser getUpdateDeleteUser() {
        return updateDeleteUser;
    }

    public static SearchForm getSearchForm() {
        return searchForm;
    }
//
//    public static InsertFormStudents getInsertFormStudents() {
//        return insertFormStudents;
//    }
//
//    public static UpdateDeleteFormStudents getUpdateDeleteFormStudents() {
//        return updateDeleteFormStudents;
//    }



}
