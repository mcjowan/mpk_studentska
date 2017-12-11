package edu.mpk.repositories;

import edu.mpk.domain.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByPrezime(String prezime);
    
    Optional<Student> findOneById(Long studentId);
    
    Page<Student> findAll(Pageable pageable);
}
