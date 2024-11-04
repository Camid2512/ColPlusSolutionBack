package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.ColPlusSolution.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findByUser(String username);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByPassword(String password);
}
