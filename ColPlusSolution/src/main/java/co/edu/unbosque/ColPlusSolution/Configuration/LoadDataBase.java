package co.edu.unbosque.ColPlusSolution.Configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.ColPlusSolution.Model.User;
import co.edu.unbosque.ColPlusSolution.Repository.UserRepository;
import co.edu.unbosque.ColPlusSolution.Util.AESUtil;

@Configuration
public class LoadDataBase {
	private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepo) {

		return args -> {
			Optional<User> found = userRepo.findByUser(AESUtil.encrypt("admin"));
			if (found.isPresent()) {
				log.info("Admin already exists,  skipping admin creating  ...");
			} else {
				userRepo.save(new User(1, "admin"	, AESUtil.encrypt("bd1"), AESUtil.encrypt("admin@admin.com"), 0));
				log.info("Preloading admin user");
			}
		};
	}

}