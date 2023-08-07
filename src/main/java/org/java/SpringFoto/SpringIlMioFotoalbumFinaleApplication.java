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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Role roleSuperAdmin = new Role("SUPERADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		roleService.save(roleSuperAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
		User userSuperAdmin = new User("superAdmin", pws, roleSuperAdmin);
		User userAdmin2 = new User("admin2", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		userService.save(userSuperAdmin);
		userService.save(userAdmin2);
		
		Foto f1 = new Foto("titolo foto admin", "descrizione foto", "https://www.donnamoderna.com/content/uploads/2020/07/Immagini-rilassanti-di-panorami-naturali.jpg", true, userAdmin);
		Foto f3 = new Foto("titolo foto admin 2", "descrizione foto 2", "https://fotografiaartistica.it/wp-content/uploads/2019/06/nasa-immagini-gratuite-dello-spazio.jpg", true, userAdmin);
		Foto f2 = new Foto("titolo foto2 superadmin", "descrizione foto2", "https://www.ideabit.com/album/foto_full/formato-immagini-webp_710.jpg", true, userSuperAdmin);
		
		Categoria c1 = new Categoria("cat 1");
		
		Categoria c2 = new Categoria("cat 2");
		
		fotoService.save(f1);
		fotoService.save(f2);
		fotoService.save(f3);
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		
		
	}

}
