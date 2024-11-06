package co.edu.unbosque.ColPlusSolution.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.ColPlusSolution.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findByUser(String username);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByPassword(String password);
}
