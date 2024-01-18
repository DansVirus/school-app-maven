package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void gettersSetters() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        assertEquals(teacher.getId(), 1);

        teacher.setFirstname("Dinos");
        assertEquals(teacher.getFirstname(), "Dinos");

        teacher.setLastname("Dasios");
        assertEquals(teacher.getFirstname(), "Dasios");
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(2, "Emi", "Ly");
        assertEquals(teacher.getId(), 2);
        assertEquals(teacher.getFirstname(), "Emi");
        assertEquals(teacher.getLastname(), "Ly");

        String expected = "2, Emi, Ly";
        assertEquals(expected, teacher.toString());
    }

}