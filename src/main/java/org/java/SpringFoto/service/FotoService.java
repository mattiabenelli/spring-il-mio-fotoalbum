package org.java.SpringFoto.service;

import java.util.List;
import java.util.Optional;

import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.repo.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {

	@Autowired
	private FotoRepo fotoRepo;
	
	
	
	public List<Foto> findAll(){
		
		return fotoRepo.findAll();
	}
	
	public Optional<Foto> findById(int id){
		
		return fotoRepo.findById(id);
	}
	
	public List<Foto> findByTitolo(String titolo){
		
		return fotoRepo.findByTitoloContaining(titolo);
	}
	
	public Foto save(Foto foto) {
		
		return fotoRepo.save(foto);
	}
	
	public void deleteFoto(Foto foto){
		
		fotoRepo.delete(foto);
	}
	
}
