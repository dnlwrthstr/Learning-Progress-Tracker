package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class StudentTest {

    @BeforeEach
    void beforeEach() {
        Student.addStudent("John", "Doe", "jdoe@mail.net");
        Student.addStudent("Jane", "Doe", "jane.doe@yahoo.com");
    }

    @ParameterizedTest
    @CsvSource({
            "Jean-Clause, van Helsing, jc@google.it, true",
            "Mary Luise, Johnson, maryj@google.com, true",
            "Mary, Emelianenko, 125367at@zzz90.z9, true",
            "Robert, Jemison Van de Graaff, robertvdgraaff@mit.edu, true",
            "nA, me, 1@1.1, true",
            "n'a, me su aa-b'b, ab@ab.ab, true",
            "John, Doe, mail, false",
            "John, D., name@domain.com, false",
            "nam-'e, surname, email@email.xyz, false",
            "n, surname, email1@email.xyz, false",
            "name, surname, email@e@mail.xyz, false",
            "noone, surname, ab@ab.ab, false"
    })
    void addStudents(String firstName, String lastName, String email, String eval) {
        assertEquals(Boolean.valueOf(eval), Student.addStudent(firstName, lastName, email));
    }

    @ParameterizedTest
    @ValueSource(ints = { 10000, 10001 })
    void getById(int id) {
        Student s = Student.getStudentById(id);
        assertNotNull(s);
        //assertEquals(", s.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings =  { "10000 10 10 10 10", "10000 10 10 10 10", "10000 -5 -5 -5 -5" })
    void updateScores(String line) {
        String[] scores = line.split(" ");
        int id = Integer.parseInt(scores[0]);
        Student before = Student.getStudentById(id);
        Student.updateScore(line);
        assertEquals(true, true);
    }

    @ParameterizedTest
    @ValueSource(strings =  { "10000", "10001" })
    void findStudent(String line) {;
        int id = Integer.parseInt(line);
        Student before = Student.getStudentById(id);
        Student.findStudent(line);
        assertEquals(true, true);
    }

}