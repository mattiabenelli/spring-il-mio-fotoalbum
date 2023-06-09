package org.java.SpringFoto.controller;

import java.util.List;
import java.util.Optional;

import org.java.SpringFoto.pojo.Categoria;
import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.service.CategoriaService;
import org.java.SpringFoto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;



@Controller
public class FotoController {

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/foto")
	public String fotoList(Model model) {
		
		List<Foto> foto = fotoService.findAll();
		
		model.addAttribute("foto", foto);
		
		return "foto-index";
		
	}
	
	@GetMapping("/foto/{id}")
	public String getFotoId(Model model,
							@PathVariable("id") int id
	){
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		model.addAttribute("foto", foto);
		
		return "foto-show";
	}
	
	@GetMapping("foto/create")
	public String CreateFoto(Model model){
		
		List<Categoria> categorie = categoriaService.findAll();
		
		model.addAttribute("foto", new Foto());
		model.addAttribute("categorie", categorie);
		
		return "foto-create";
	}
	
	@PostMapping("foto/create")
	public String storeFoto(Model model,
						@Valid @ModelAttribute Foto foto,
						BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Categoria> categorie = categoriaService.findAll();
			
			model.addAttribute("foto", foto);
			model.addAttribute("categorie", categorie);
			
			model.addAttribute("errors", bindingResult);
			
			
			return "foto-create";
		}
		
		fotoService.save(foto);
		
		return "redirect:/foto";
	}
	
	@GetMapping("foto/update/{id}")
	public String editFoto(Model model,
							@PathVariable int id) {
		
		List<Categoria> categorie = categoriaService.findAll();
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		
		return "foto-update";
	}
	
	@PostMapping("foto/update/{id}")
	public String updateFoto(Model model,
						@Valid @ModelAttribute Foto foto,
						BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Categoria> categorie = categoriaService.findAll();
			
			model.addAttribute("foto", foto);
			model.addAttribute("categorie", categorie);
			
			model.addAttribute("errors", bindingResult);
			
			
			return "foto-update";
		}
		
		fotoService.save(foto);
		
		return "redirect:/foto";
	}
}
