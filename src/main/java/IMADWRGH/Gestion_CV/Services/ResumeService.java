package IMADWRGH.Gestion_CV.Services;

import IMADWRGH.Gestion_CV.Model.Companies;
import IMADWRGH.Gestion_CV.Model.Resume;
import IMADWRGH.Gestion_CV.Model.Skills;
import IMADWRGH.Gestion_CV.Repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final SkillsService skillsService;
    private final InformationService informationService;
    private final CompaniesService companiesService;
    private final ResumeRepository resumeRepository;

    public ResumeService(SkillsService skillsService, InformationService informationService, CompaniesService companiesService, ResumeRepository resumeRepository) {
        this.skillsService = skillsService;
        this.informationService = informationService;
        this.companiesService = companiesService;
        this.resumeRepository = resumeRepository;
    }


    public Resume create(Resume resume){
        for (Skills skill : resume.getIdSkills()) {
            skillsService.save(skill);
        }
        for (Companies companies:resume.getIdComps()){
            companiesService.save(companies);
        }
        informationService.save(resume.getIdInfons());
        resumeRepository.insert(resume);
        return resume;
    }
}
