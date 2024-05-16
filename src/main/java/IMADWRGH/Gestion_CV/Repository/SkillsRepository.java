package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Skills;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class SkillsRepository implements GenericRepository<Skills> {
    private final JdbcTemplate template;

    public SkillsRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Skills> getAll() {
        return template.query("select * from skills",
                (resultSet, rownumber) -> {
                    Skills skills = new Skills();
                    skills.setId(resultSet.getInt("id"));
                    skills.setName(resultSet.getString("name"));
                    skills.setLevel(resultSet.getString("level"));
                    return skills;
                });
    }

    @Override
    public Optional<Skills> findById(int id) {
        return Optional.ofNullable(template.queryForObject(
                "select * from skills where id=?",
                Skills.class,id));
    }

    @Override
    public int deleteById(int id) {
        return template.update("delete from skills where id=?", new Object[] {id});
    }

    @Override
    public int insert(Skills skills) {
        String insertSql = "insert into skills (name, level) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, skills.getName());
            ps.setString(2, skills.getLevel());
            return ps;
        }, keyHolder);
        System.out.println(keyHolder.getKey().intValue());
        return  keyHolder.getKey().intValue();
    }


    @Override
    public int update(Skills skills) {
        return template.update("update skills " + " set name = ?, name = ?, level = ? " + " where id = ?",
               skills.getId(), skills.getName(), skills.getLevel());
    }

}