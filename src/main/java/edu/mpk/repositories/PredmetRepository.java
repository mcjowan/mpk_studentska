package edu.mpk.repositories;

import edu.mpk.domain.Predmet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PredmetRepository extends CrudRepository<Predmet, Long> {

    List<Predmet> findByNaziv(String naziv);
    
    Optional<Predmet> findOneById(Long predmetId);
    
    Page<Predmet> findAll(Pageable pageable);
}
