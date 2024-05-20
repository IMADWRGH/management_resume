package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Companies;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeCompanyRepository {
    private final JdbcTemplate template;

    public ResumeCompanyRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int deleteById(int id) {
        return template.update("delete from resume_company where id_resume=?", new Object[] {id});
    }
    public List<Companies> findById(int id){
        System.out.println("RC test");
        String sql ="select c.* " +"from companies c " +"inner join resume_company rc on c.id = rc.id_company " +
                "where rc.id_resume="+id;

        return template.query(sql, (rs, rownumber) -> {
            Companies companies=new Companies();
            companies.setId(rs.getInt(1));
            companies.setName(rs.getString(2));
            companies.setDepartment(rs.getString(3));
            companies.setYearExpression(rs.getString(4));
            return companies;
        });
    }

    public int insert(int idResume, int idCompany) {
        String idResumeStr = String.valueOf(idResume);
        String idCompanyStr = String.valueOf(idCompany);
        return template.update("insert into resume_company (id_resume, id_company) VALUES (?,?)",
                idResumeStr, idCompanyStr);
    }

}
