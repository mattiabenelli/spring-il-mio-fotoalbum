package org.java.SpringFoto;

import org.java.SpringFoto.pojo.Categoria;
import org.java.SpringFoto.pojo.Foto;
import org.java.SpringFoto.service.CategoriaService;
import org.java.SpringFoto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumFinaleApplication implements CommandLineRunner{

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumFinaleApplication.class, args);
		
		System.out.println("helloworld");
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Foto f1 = new Foto("titolo foto", "descrizione foto", "url foto", true);
		
		Categoria c1 = new Categoria("cat 1");
		
		Categoria c2 = new Categoria("cat 2");
		
		fotoService.save(f1);
		
		categoriaService.save(c1);
		categoriaService.save(c2);
		
		
	}

}
