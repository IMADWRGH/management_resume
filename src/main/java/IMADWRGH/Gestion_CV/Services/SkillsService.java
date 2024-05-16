package IMADWRGH.Gestion_CV.Services;

import IMADWRGH.Gestion_CV.Model.Information;
import IMADWRGH.Gestion_CV.Model.Skills;
import IMADWRGH.Gestion_CV.Repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsService {
    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public List<Skills> getAll() {
        return skillsRepository.getAll();
    }

    public Optional<Skills> getById(int id) {
        return skillsRepository.findById(id);
    }

    public int deleteById(int id) {
        return skillsRepository.deleteById(id);
    }

    public int save(Skills skills) {
        return  skillsRepository.insert(skills);
    }

    public int update(Skills skills) {
        return skillsRepository.update(skills);
    }
}
