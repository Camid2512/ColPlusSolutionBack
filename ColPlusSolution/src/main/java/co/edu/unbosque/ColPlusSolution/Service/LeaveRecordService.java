package co.edu.unbosque.ColPlusSolution.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.LeaveRecord;
import co.edu.unbosque.ColPlusSolution.Repository.LeaveRecordRepository;

@Service
public class LeaveRecordService implements CRUDOperation<LeaveRecord> {

    @Autowired
    private LeaveRecordRepository leaveRecordRepository;

    @Override
    public int create(LeaveRecord data) {
        leaveRecordRepository.save(data);
        return 1;
    }

    @Override
    public List<LeaveRecord> getAll() {
        return (List<LeaveRecord>) leaveRecordRepository.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (leaveRecordRepository.existsById(id)) {
            leaveRecordRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, LeaveRecord newData) {
        Optional<LeaveRecord> record = leaveRecordRepository.findById(id);
        if (record.isPresent()) {
            LeaveRecord updatedRecord = record.get();
            updatedRecord.setOnVacation(newData.getOnVacation());
            updatedRecord.setOnSickLeave(newData.getOnSickLeave());
            updatedRecord.setBonus(newData.getBonus());
            updatedRecord.setTransportAllowance(newData.getTransportAllowance());
            leaveRecordRepository.save(updatedRecord);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return leaveRecordRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return leaveRecordRepository.existsById(id);
    }
}
