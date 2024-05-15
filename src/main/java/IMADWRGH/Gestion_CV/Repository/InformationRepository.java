package IMADWRGH.Gestion_CV.Repository;

import IMADWRGH.Gestion_CV.Model.Companies;
import IMADWRGH.Gestion_CV.Model.Information;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return Optional.ofNullable(template.queryForObject(
                " select * from information where id=?",
              Information.class,id));
    }

    @Override
    public int deleteById(int id) {
        return template.update("delete from information where id=?", new Object[] {id});
    }

    @Override
    public int insert(Information information) {
        return template.update(" insert into information ( full_name, email) " + "values( ?, ?)",
                information.getFull_name(), information.getEmail());
    }

    @Override
    public int update(Information information) {
        return template.update(" update information " + " set full_name = ?,email = ?" + " where id = ?",
               information.getId(), information.getFull_name(), information.getEmail());    }


    }
