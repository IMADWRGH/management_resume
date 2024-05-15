package IMADWRGH.Gestion_CV.Repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    List<T> getAll();

    Optional<T> findById(int id);

    int deleteById(int id);

    int insert(T entity);

    int update(T entity);
}
