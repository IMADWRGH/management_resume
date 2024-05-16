package IMADWRGH.Gestion_CV.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeCompanyRepository {
    private final JdbcTemplate template;

    public ResumeCompanyRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int deleteById(int id) {
        return template.update("delete from resume_company where id_resume=?", new Object[] {id});
    }

    public int insert(int idResume, int idCompany) {
        String idResumeStr = String.valueOf(idResume);
        String idCompanyStr = String.valueOf(idCompany);
        return template.update("insert into resume_company (id_resume, id_company) VALUES (?,?)",
                idResumeStr, idCompanyStr);
    }

}
