package IMADWRGH.Gestion_CV.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeSkillsRepository {
    private final JdbcTemplate template;

    public ResumeSkillsRepository(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public int deleteById(int id) {
        return template.update("delete from resume_skills where id_resume=?", new Object[] {id});
    }

    public int insert(int idResume, int idCompany) {
        String idResumeStr = String.valueOf(idResume);
        String idSkills = String.valueOf(idCompany);
        return template.update("insert into resume_skills (id_resume, id_skills) VALUES (?,?)",
                idResumeStr, idSkills);
    }
}
