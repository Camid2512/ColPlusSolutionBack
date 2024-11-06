package co.edu.unbosque.ColPlusSolution.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.ColPlusSolution.model.Payroll;

public interface PayrollRepository extends CrudRepository<Payroll, Integer> {

	public Optional<Payroll> findByEmployee(String employeeName);

}
