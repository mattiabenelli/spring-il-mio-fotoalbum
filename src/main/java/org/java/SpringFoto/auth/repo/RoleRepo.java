package org.java.SpringFoto.auth.repo;

import org.java.SpringFoto.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
