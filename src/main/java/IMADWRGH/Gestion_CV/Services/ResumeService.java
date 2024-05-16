package IMADWRGH.Gestion_CV.Services;

import IMADWRGH.Gestion_CV.Model.Companies;
import IMADWRGH.Gestion_CV.Model.Resume;
import IMADWRGH.Gestion_CV.Model.Skills;
import IMADWRGH.Gestion_CV.Repository.ResumeCompanyRepository;
import IMADWRGH.Gestion_CV.Repository.ResumeRepository;
import IMADWRGH.Gestion_CV.Repository.ResumeSkillsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {

    private final SkillsService skillsService;
    private final InformationService informationService;
    private final CompaniesService companiesService;
    private final ResumeRepository resumeRepository;
    private final ResumeCompanyRepository resumeCompanyRepository;

    private  final ResumeSkillsRepository resumeSkillsRepository;

    public ResumeService(SkillsService skillsService, InformationService informationService, CompaniesService companiesService, ResumeRepository resumeRepository, ResumeCompanyRepository resumeCompanyRepository, ResumeSkillsRepository resumeSkillsRepository) {
        this.skillsService = skillsService;
        this.informationService = informationService;
        this.companiesService = companiesService;
        this.resumeRepository = resumeRepository;
        this.resumeCompanyRepository = resumeCompanyRepository;
        this.resumeSkillsRepository = resumeSkillsRepository;
    }


    public Resume create(Resume resume){
       var idResume= resumeRepository.insert(resume);
        for (Skills skill : resume.getIdSkills()) {
           var id= skillsService.save(skill);
            resumeSkillsRepository.insert(idResume,id);
        }
        for (Companies companies:resume.getIdComps()){
            var id = companiesService.save(companies);
            resumeCompanyRepository.insert(idResume, id);
        }
            informationService.save(resume.getIdInfons());

        return resume;
    }
    public Optional<Resume> getResume(int id){
       return resumeRepository.findById(id);
    }
}
