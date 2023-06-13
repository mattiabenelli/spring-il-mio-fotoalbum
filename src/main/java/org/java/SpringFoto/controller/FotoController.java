package org.java.SpringFoto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.java.SpringFoto.auth.pojo.Role;
import org.java.SpringFoto.auth.pojo.User;
import org.java.SpringFoto.auth.serv.RoleService;
import org.java.SpringFoto.auth.serv.UserService;
import org.java.SpringFoto.pojo.Categoria;
import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.service.CategoriaService;
import org.java.SpringFoto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/admin/foto")
	public String fotoList(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		
		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

		Set<String> role = authentication1.getAuthorities().stream()
		     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		
		
		String[] array = role.toArray(new String[0]);
        System.out.println(Arrays.toString(array));
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length; i++) {
           sb.append(array[i]);
        }
        String str = sb.toString();
        System.out.println(str);
		
		List<User> users = userService.findAll();
		
		List<Foto> foto = fotoService.findAll();
			
		model.addAttribute("str", str);
			
		if(str.equals("SUPERADMIN")) {
			
			model.addAttribute("foto", foto);
			
		}
		else if(str.equals("ADMIN")){
			for(User u : users) {
				
				if(currentPrincipalName.equals(u.getUsername()) ) {
					
					for(Foto f : foto) {
						
						if(u.getId() == f.getUser().getId()) {
							
							System.out.println("sium3");
							
							model.addAttribute("f", f);
							break;
							
						}
					}		
				}	
			}
		}

		
	return "foto-index";	
	}
	
	@GetMapping("/admin/foto/{id}")
	public String getFotoId(Model model,
							@PathVariable("id") int id
	){
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		model.addAttribute("foto", foto);
		
		return "foto-show";
	}
	
	@GetMapping("/admin/foto/create")
	public String CreateFoto(Model model){
		
		List<Categoria> categorie = categoriaService.findAll();
		
		model.addAttribute("foto", new Foto());
		model.addAttribute("categorie", categorie);
		
		return "foto-create";
	}
	
	@PostMapping("/admin/foto/create")
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
		
		foto.setVisibile(true);
		fotoService.save(foto);
		
		return "redirect:/admin/foto";
	}
	
	@GetMapping("/admin/foto/update/{id}")
	public String editFoto(Model model,
							@PathVariable int id) {
		
		List<Categoria> categorie = categoriaService.findAll();
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		
		return "foto-update";
	}
	
	@PostMapping("/admin/foto/update/{id}")
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
		
		return "redirect:/admin/foto";
	}
	
	@GetMapping("/admin/foto/delete/{id}")
	public String deleteFoto(
			@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		fotoService.deleteFoto(foto);
		
		return "redirect:/admin/foto";
	}
	

	@GetMapping("/admin/foto/hide/{id}")
	public String hideFoto(@PathVariable int id) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		if(foto.getVisibile() == true) {
			
			foto.setVisibile(false);
		}
		else if(foto.getVisibile() == false) {
			
			foto.setVisibile(true);
		}
		
		fotoService.save(foto);
		
		return "redirect:/admin/foto";
		
	}
}
