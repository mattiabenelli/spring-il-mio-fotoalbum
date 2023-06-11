package org.java.SpringFoto.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {

	@Autowired
	private FotoService fotoService;
	
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
}
