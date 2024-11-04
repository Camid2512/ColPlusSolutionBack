package co.edu.unbosque.ColPlusSolution.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.User;
import co.edu.unbosque.ColPlusSolution.Repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceOld implements CRUDOperation<User> {

    @Autowired
    private UserRepository userRep;

    @Override
    public int create(User data) {
        if (!userRep.findByEmail(data.getEmail()).isPresent()) {
            userRep.save(data);
            return 1;
        }
        return 0;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRep.findAll();
    }

    public User getByUsername(String username) {
        Optional<User> found = userRep.findByUser(username);
        return found.orElse(null);
    }

    @Override
    public int deleteById(Long id) {
        if (userRep.existsById(id.intValue())) {
            userRep.deleteById(id.intValue());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, User newData) {
        Optional<User> found = userRep.findById(id.intValue());
        if (found.isPresent()) {
            User existingUser = found.get();
            existingUser.setUser(newData.getUser());
            existingUser.setPassword(newData.getPassword());
            existingUser.setEmail(newData.getEmail());
            existingUser.setUserType(newData.getUserType());
            userRep.save(existingUser);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return userRep.count();
    }

    @Override
    public boolean exist(Long id) {
        return userRep.existsById(id.intValue());
    }

    public boolean userExistsByEmail(String email) {
        return userRep.findByEmail(email).isPresent();
    }

    public boolean userExistsByPassword(String password) {
        return userRep.findByPassword(password).isPresent();
    }
}
