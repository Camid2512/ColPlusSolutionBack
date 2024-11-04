package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.ColPlusSolution.Model.LeaveRecord;

public interface LeaveRecordRepository extends CrudRepository<LeaveRecord, Long> {

    Optional<LeaveRecord> findByOnVacation(Boolean onVacation);
    
    Optional<LeaveRecord> findByOnSickLeave(Boolean onSickLeave);
    
    Optional<LeaveRecord> findByDaysWorkedThisMonth(int daysWorkedThisMonth);
}
