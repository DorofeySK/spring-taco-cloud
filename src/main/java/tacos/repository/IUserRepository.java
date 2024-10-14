package tacos.repository;

import org.springframework.data.repository.CrudRepository;

import tacos.users.User;

public interface IUserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
