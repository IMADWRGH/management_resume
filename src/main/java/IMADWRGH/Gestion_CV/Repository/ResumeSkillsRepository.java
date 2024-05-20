package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Companies;
import IMADWRGH.Gestion_CV.Model.Skills;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public List<Skills> findById(int id) {
        System.out.println("RS test");
        String sql = "select s.* " + "from skills s " + "inner join resume_skills rs on s.id = rs.id_skills " +
                "where rs.id_resume=" + id;

        return template.query(sql, (rs, rownumber) -> {
            Skills skills = new Skills();
            skills.setId(rs.getInt(1));
            skills.setName(rs.getString(2));
            skills.setLevel(rs.getString(3));
            return skills;
        });
    }
}
