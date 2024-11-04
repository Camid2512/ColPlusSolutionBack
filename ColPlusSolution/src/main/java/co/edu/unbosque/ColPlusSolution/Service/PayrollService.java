package co.edu.unbosque.ColPlusSolution.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.Payroll;
import co.edu.unbosque.ColPlusSolution.Repository.PayrollRepository;

@Service
public class PayrollService implements CRUDOperation<Payroll> {

    @Autowired
    private PayrollRepository payrollRepository;

    @Override
    public int create(Payroll data) {
        payrollRepository.save(data);
        return 1;
    }

    @Override
    public List<Payroll> getAll() {
        return (List<Payroll>) payrollRepository.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (payrollRepository.existsById(id)) {
            payrollRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, Payroll newData) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            Payroll updatedPayroll = payroll.get();
            updatedPayroll.setEmployeeName(newData.getEmployeeName());
            updatedPayroll.setDepartment(newData.getDepartment());
            updatedPayroll.setPosition(newData.getPosition());
            updatedPayroll.setSalary(newData.getSalary());
            payrollRepository.save(updatedPayroll);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return payrollRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return payrollRepository.existsById(id);
    }
}
