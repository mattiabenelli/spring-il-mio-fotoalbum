package org.java.SpringFoto.auth.repo;

import java.util.Optional;

import org.java.SpringFoto.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}
