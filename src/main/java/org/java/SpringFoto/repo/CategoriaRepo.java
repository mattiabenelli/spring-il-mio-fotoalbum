package org.java.SpringFoto.repo;

import org.java.SpringFoto.pojo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

}
