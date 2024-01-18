package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceImplTest {

    private static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() {
        teacherService = new TeacherServiceImpl(teacherDAO);
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyTeachers();

    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetTeacher() throws TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Anna", "Giannoutsou");
        teacherService.insertTeacher(teacherDTO);

        List<Teacher> teacherList = teacherService.getTeacherByLastname(teacherDTO.getLastname());

        assertEquals(1, teacherList.size());
        assertEquals(teacherList.get(0).getLastname(), teacherDTO.getLastname());
        assertEquals(teacherList.get(0).getFirstname(), teacherDTO.getFirstname());
    }

    @Test
    public void updateTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Anna2", "Giannoutsou2");
        teacherService.updateTeacher(teacherDTO);

        List<Teacher> teacherList = teacherService.getTeacherByLastname(teacherDTO.getLastname());
        assertEquals(teacherList.get(0).getLastname(), teacherDTO.getLastname());
        assertEquals(teacherList.get(0).getFirstname(), teacherDTO.getFirstname());
    }

    @Test
    public void updateInvalidTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(10, "Anna2", "Giannoutsou2");


        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.updateTeacher(teacherDTO);
        });
    }

    @Test
    public void deleteTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Anna", "Giannoutsou");
        teacherService.deleteTeacher(teacherDTO.getId());

        List<Teacher> teacher = teacherService.getTeacherByLastname(teacherDTO.getLastname());
        assertEquals(0, teacher.size());
    }

    @Test
    public void deleteInvalidTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherDTO teacherDTO = new TeacherDTO(10, "Anna2", "Giannoutsou2");

        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(teacherDTO.getId());
        });
    }

    public static void createDummyTeachers() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Dinos");
        teacher.setLastname("Dasios");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Mark");
        teacher.setLastname("Zouk");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Elon");
        teacher.setLastname("Musk");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("John");
        teacher.setLastname("Lennon");
        teacherDAO.insert(teacher);
    }

}