package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Information;
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
public class InformationRepository  implements GenericRepository<Information> {

    private final JdbcTemplate template;

    public InformationRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Information> getAll() {
        return template.query("select * from information",
                (resultSet, rownumber) -> {
                    Information information= new Information();
                    information.setId(resultSet.getInt("id"));
                    information.setFull_name(resultSet.getString("fullName"));
                    information.setEmail(resultSet.getString("department"));
                    return information;
                });
    }

    @Override
    public Optional<Information> findById(int id) {
        System.out.println("infons id "+id);
        String sql="select * from information where id=?";
        Information information =template.queryForObject(sql, new BeanPropertyRowMapper<>(Information.class),id);
        return Optional.ofNullable(information);
    }

    @Override
    public int deleteById(int id) {
        return template.update("delete from information where id=?", new Object[] {id});
    }

    @Override
    public int insert(Information information) {
        String insertSql = "insert into information (full_name, email) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, information.getFull_name());
            ps.setString(2, information.getEmail());
            return ps;
        }, keyHolder);
        System.out.println(keyHolder.getKey().intValue());
        return  keyHolder.getKey().intValue();
    }

    @Override
    public int update(Information information) {
        return template.update(" update information " + " set full_name = ?,email = ?" + " where id = ?",
               information.getId(), information.getFull_name(), information.getEmail());    }


    }
