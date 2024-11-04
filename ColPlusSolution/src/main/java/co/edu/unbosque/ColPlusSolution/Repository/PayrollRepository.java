package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.ColPlusSolution.Model.Payroll;

public interface PayrollRepository extends CrudRepository<Payroll, Long> {

    Optional<Payroll> findByEmployeeName(String employeeName);

    Optional<Payroll> findByDepartment(String department);
    
    Optional<Payroll> findByPosition(String position);
}
