package edu.mpk.services;

import edu.mpk.domain.Student;
import edu.mpk.repositories.StudentRepository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Student.
 */
@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    
    @Inject
    private StudentRepository studentRepository;

//    @Inject
//    private StudentMapper studentMapper;

//    @Inject
//    private StudentSearchRepository StudentSearchRepository;

    /**
     * Save a Student.
     *
     * @param StudentDTO the entity to save
     * @return the persisted entity
     */
//    public StudentDTO save(StudentDTO StudentDTO) {
//        log.debug("Request to save Student : {}", StudentDTO);
//        Student student = studentMapper.studentDTOToStudent(StudentDTO);
//        student = studentRepository.save(student);
//        StudentDTO result = studentMapper.studentToStudentDTO(student);
//        //StudentSearchRepository.save(Student);
//        return result;
//    }

    /**
     *  Get all the Students.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
//    @Transactional(readOnly = true) 
//    public Page<StudentDTO> findAll(Pageable pageable) {
//        log.debug("Request to get all Students");
//        Page<Student> result = studentRepository.findAll(pageable);
//        return result.map(Student -> studentMapper.studentToStudentDTO(Student));
//    }

    /**
     *  Get one Student by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
//    @Transactional(readOnly = true) 
//    public StudentDTO findOne(Long id) {
//        log.debug("Request to get Student : {}", id);
//        Student Student = studentRepository.findOne(id);
//        StudentDTO StudentDTO = studentMapper.studentToStudentDTO(Student);
//        return StudentDTO;
//    }

    /**
     *  Delete the  Student by id.
     *
     *  @param id the id of the entity
     */
//    public void delete(Long id) {
//        log.debug("Request to delete Student : {}", id);
//        studentRepository.delete(id);
//        //StudentSearchRepository.delete(id);
//    }

    /**
     * Search for the Student corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    /*
    @Transactional(readOnly = true)
    public Page<StudentDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Students for query {}", query);
        Page<Student> result = StudentSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(Student -> StudentMapper.studentToStudentDTO(Student));
    }
    */
    

    public Iterable<Student> listAllStudents() {
    	log.debug("Request to get all Students");
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findOne(id);
    }

    /**
     * 
     * @param student
     * @return the persisted entity
     */
    public Student saveStudent(Student student) {
    	log.debug("Request to save Student : {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
    	log.debug("Request to delete Student : {}", id);
        studentRepository.delete(id);
    }
}
