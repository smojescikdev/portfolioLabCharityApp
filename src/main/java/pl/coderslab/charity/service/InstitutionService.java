package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    //Get all Institutions from database
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public Institution findById(Long institutionIds) {
        return institutionRepository.findById(institutionIds);
    }

    public Institution getOne(int id) {
        return institutionRepository.getReferenceById(id);
    }

    public void deleteInstitution(int id) {
        institutionRepository.deleteById(id);

    }
}
