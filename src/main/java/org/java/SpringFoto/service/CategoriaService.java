package org.java.SpringFoto.service;

import java.util.List;
import java.util.Optional;

import org.java.SpringFoto.pojo.Categoria;
import org.java.SpringFoto.repo.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepo categoriaRepo;
	
public List<Categoria> findAll(){
		
		return categoriaRepo.findAll();
	}
	
	public Optional<Categoria> findById(int id){
		
		return categoriaRepo.findById(id);
	}
	
	public void deleteCat(Categoria categoria){
		
		categoriaRepo.delete(categoria);
	}
	
	public Categoria save(Categoria categoria) {
		
		return categoriaRepo.save(categoria);
	}
}
