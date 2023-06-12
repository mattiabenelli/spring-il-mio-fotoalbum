package org.java.SpringFoto.service;

import java.util.List;
import java.util.Optional;

import org.java.SpringFoto.pojo.Messaggio;
import org.java.SpringFoto.repo.MessaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaggioService {

	@Autowired
	private MessaggioRepo messaggioRepo;
	
	
	
	public List<Messaggio> findAll(){
		
		return messaggioRepo.findAll();
	}
	
	public Optional<Messaggio> findById(int id){
		
		return messaggioRepo.findById(id);
	}
	

	public Messaggio save(Messaggio messaggio) {
		
		return messaggioRepo.save(messaggio);
	}
	
}
