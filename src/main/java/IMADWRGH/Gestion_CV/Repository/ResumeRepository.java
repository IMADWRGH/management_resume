package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.DTO.ResumeDTO;
import IMADWRGH.Gestion_CV.Model.Resume;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ResumeRepository implements GenericRepository<Resume>{
    private final JdbcTemplate template;
    private final InformationRepository informationRepository;
    private final ResumeCompanyRepository resumeCompanyRepository;
    private final ResumeSkillsRepository resumeSkillsRepository;

    public ResumeRepository(JdbcTemplate template, InformationRepository informationRepository, ResumeCompanyRepository resumeCompanyRepository, ResumeSkillsRepository resumeSkillsRepository) {
        this.template = template;
        this.informationRepository = informationRepository;
        this.resumeCompanyRepository = resumeCompanyRepository;
        this.resumeSkillsRepository = resumeSkillsRepository;
    }

    @Override
    public List<Resume> getAll() {
        return template.query("select * from resume",
                (resultSet, rownumber) -> {
                    Resume resume = new Resume();
                    resume.setId(resultSet.getInt("id"));
                    resume.setProfile(resultSet.getString("profile"));
                    return resume;
                });
    }

    @Override
    public Optional<Resume> findById(int id) {
        String sql="select * from resume where id=?";
        ResumeDTO resumedto =template.queryForObject(sql, new BeanPropertyRowMapper<>(ResumeDTO.class),id);
        System.out.println(resumedto.getProfile());
        Resume resume=new Resume();
        resume.setId(resumedto.getId());
        resume.setProfile(resumedto.getProfile());
        System.out.println(resumedto.getIdInfons());
        resume.setIdInfons(informationRepository.findById(resumedto.getIdInfons()).get());
        resume.setIdComps(resumeCompanyRepository.findById(resume.getId()));
        resume.setIdSkills(resumeSkillsRepository.findById(resume.getId()));
      return Optional.of(resume);
    }
    @Override
    public int  deleteById(int id) {
            System.out.println("delete work");
            resumeCompanyRepository.deleteById(id);
            resumeSkillsRepository.deleteById(id);
            template.update("DELETE FROM information WHERE id=?",id);
            System.out.println("test");
            return template.update("DELETE FROM resume WHERE id=?",id);
    }


    public int insert(Resume resume,int idInfons) {
//        return template.update("insert into resume ( profile, idInfons) " + "values( ?, ?)",
//                resume.getProfile(), idInfons);
        String sql = "insert into resume (profile, idInfons) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, resume.getProfile());
            ps.setInt(2, idInfons);
            return ps;
        }, keyHolder);
        System.out.println(keyHolder.getKey().intValue());
        return  keyHolder.getKey().intValue();
    }


    @Override
    public int update(Resume resume) {
        return template.update("update resume " + " set  profile= ? " + " where id = ?",
                resume.getId(), resume.getProfile());
    }

    @Override
    public int insert(Resume resume){
        return 0;
    }
}
