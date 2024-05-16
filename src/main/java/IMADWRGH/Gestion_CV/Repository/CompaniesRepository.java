package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Companies;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CompaniesRepository implements GenericRepository<Companies>{
    private final JdbcTemplate template;

    public CompaniesRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Companies> getAll() {
        return template.query("select * from companies",
                (resultSet, rownumber) -> {
                    Companies companies = new Companies();
                    companies.setId(resultSet.getInt("id"));
                    companies.setName(resultSet.getString("name"));
                    companies.setDepartment(resultSet.getString("department"));
                    companies.setYearExpression(resultSet.getString("yearExpression"));
                    return companies;
                });
    }

    @Override
    public Optional<Companies> findById(int id) {
        return Optional.ofNullable(template.queryForObject(
                "select * from companies where id=?",
                Companies.class,id));
    }

    @Override
    public int deleteById(int id) {
        return template.update("delete from companies where id=?", new Object[] {id});
    }

    @Override
    public int insert(Companies companies) {
        String sql = "insert into companies (name,department,yearExpression) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, companies.getName());
            ps.setString(2,companies.getDepartment());
            ps.setString(3,companies.getYearExpression());
            return ps;
        }, keyHolder);
        System.out.println(keyHolder.getKey().intValue());
        return  keyHolder.getKey().intValue();
    }

    @Override
    public int update(Companies companies) {
        return template.update("update companies " + " set name = ?, departemt = ?,yearExpression=? " + " where id = ?",
               companies.getId(), companies.getName(), companies.getDepartment(),companies.getYearExpression());
    }

}
