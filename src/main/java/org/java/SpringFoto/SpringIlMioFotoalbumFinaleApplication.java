package org.java.SpringFoto;

import org.java.SpringFoto.pojo.Categoria;
import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.service.CategoriaService;
import org.java.SpringFoto.service.FotoService;
import org.java.SpringFoto.auth.pojo.Role;
import org.java.SpringFoto.auth.pojo.User;
import org.java.SpringFoto.auth.serv.RoleService;
import org.java.SpringFoto.auth.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumFinaleApplication implements CommandLineRunner{

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumFinaleApplication.class, args);
		
		System.out.println("helloworld");
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		
		Foto f1 = new Foto("titolo foto", "descrizione foto", "url foto", true);
		
		Categoria c1 = new Categoria("cat 1");
		
		Categoria c2 = new Categoria("cat 2");
		
		fotoService.save(f1);
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		
		
	}

}
