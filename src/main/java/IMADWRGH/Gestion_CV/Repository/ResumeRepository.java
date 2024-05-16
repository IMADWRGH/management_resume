package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Information;
import IMADWRGH.Gestion_CV.Model.Resume;
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
public class ResumeRepository implements GenericRepository<Resume>{
    private final JdbcTemplate template;

    public ResumeRepository(JdbcTemplate template) {
        this.template = template;
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
        return Optional.ofNullable(template.queryForObject(
                "select * from resume where id=?",
                Resume.class,id));
    }

    @Override
    public int deleteById(int id) {
        return template.update("delete from resume where id=?", new Object[] {id});

    }

    @Override
    public int insert(Resume resume) {
        String profile = resume.getProfile();
        Information information = resume.getIdInfons();
        String sql = "insert into resume (profile, idInfons) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, profile);
            ps.setLong(2, information.getId());
            return ps;
        }, keyHolder);
    }


    @Override
    public int update(Resume resume) {
        return template.update("update resume " + " set  profile= ? " + " where id = ?",
                resume.getId(), resume.getProfile());
    }
}
