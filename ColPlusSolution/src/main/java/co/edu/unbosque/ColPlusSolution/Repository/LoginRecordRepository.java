package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.ColPlusSolution.Model.LoginRecord;

public interface LoginRecordRepository extends CrudRepository<LoginRecord, Long> {

    Optional<LoginRecord> findByUser(String username);
}
