package edu.mpk.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import edu.mpk.configuration.RepositoryConfiguration;
import edu.mpk.domain.Student;
import edu.mpk.repositories.StudentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Test
    public void testSaveStudent(){
        //setup student
        Student student = new Student();
        student.setPrezime("Baha");
        student.setIme("Baja");
        //student.setId(123L);

        //save student, verify has ID value after save
        assertNull(student.getId()); //null before save
        studentRepository.save(student);
        assertNotNull(student.getId()); //not null after save

        //fetch from DB
        Student fetchedStudent = studentRepository.findOne(student.getId());

        //should not be null
        assertNotNull(fetchedStudent);

        //should equal
        assertEquals(student.getId(), fetchedStudent.getId());
        assertEquals(student.getIme(), fetchedStudent.getIme());

        //update description and save
        fetchedStudent.setPrezime("Mali");
        studentRepository.save(fetchedStudent);

        //get from DB, should be updated
        Student fetchedUpdatedStudent = studentRepository.findOne(fetchedStudent.getId());
        assertEquals(fetchedStudent.getIme(), fetchedUpdatedStudent.getIme());

        //verify count of students in DB
        long studentCount = studentRepository.count();
        assertEquals(studentCount, 1);

        //get all students, list should only have one
//        Iterable<Student> students = studentRepository.findAll();
//
//        int count = 0;
//
//        for(Student p : students){
//            count++;
//        }
//
//        assertEquals(count, 1);
    }
}
