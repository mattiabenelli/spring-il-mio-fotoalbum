package org.java.SpringFoto.api.controller;

import java.util.List;

import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.pojo.Messaggio;
import org.java.SpringFoto.repo.CategoriaRepo;
import org.java.SpringFoto.repo.MessaggioRepo;
import org.java.SpringFoto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private MessaggioRepo messaggioRepo;
	
	@Autowired
	private CategoriaRepo categoriaRepo;
	
	@GetMapping("/foto")
	public ResponseEntity<List<Foto>> getApiFoto(){
		
		List<Foto> foto = fotoService.findAll();
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@GetMapping("/foto/{id}")
	public ResponseEntity<Foto> getApiSingle(@PathVariable int id){
		
		Foto foto = fotoService.findById(id).get();
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@GetMapping("/foto/search")
	public ResponseEntity<List<Foto>> getApiSearch(@RequestParam("titolo") String titolo){
		
		List<Foto> foto = fotoService.findByTitolo(titolo);
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
		
	}
	
	@PostMapping("foto/message")
	public ResponseEntity<Messaggio> SendMessaggio(@RequestBody(required = true) Messaggio messaggio){
		
		messaggio = messaggioRepo.save(messaggio);
		
		return new ResponseEntity<>(messaggio, HttpStatus.OK);
	}
}
