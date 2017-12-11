package edu.mpk.services;

import edu.mpk.domain.Predmet;
import edu.mpk.repositories.PredmetRepository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PredmetService {

    private final Logger log = LoggerFactory.getLogger(PredmetService.class);
    
    @Inject
    private PredmetRepository predmetRepository;
    
    public Iterable<Predmet> listAllPredmeti() {
    	log.debug("Request to get all Predmeti");
        return predmetRepository.findAll();
    }

    public Predmet getPredmetById(Long id) {
        return predmetRepository.findOne(id);
    }

    public Predmet savePredmet(Predmet predmet) {
    	log.debug("Request to save Predmet : {}", predmet);
        return predmetRepository.save(predmet);
    }

    public void deletePredmet(Long id) {
    	log.debug("Request to delete Predmet : {}", id);
    	predmetRepository.delete(id);
    }
}
