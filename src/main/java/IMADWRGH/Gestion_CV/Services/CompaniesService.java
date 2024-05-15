package IMADWRGH.Gestion_CV.Services;

import IMADWRGH.Gestion_CV.Model.Companies;
import IMADWRGH.Gestion_CV.Model.Skills;
import IMADWRGH.Gestion_CV.Repository.CompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesService {

    private final CompaniesRepository companiesRepository;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }


    public List<Companies> getAll() {
        return companiesRepository.getAll();
    }

    public Optional<Companies> getById(int id) {
        return companiesRepository.findById(id);
    }

    public int deleteById(int id) {
        return companiesRepository.deleteById(id);
    }

    public int save(Companies companies) {
        return companiesRepository.insert(companies);
    }

    public int update(Companies companies) {
        return companiesRepository.update(companies);
    }
}
