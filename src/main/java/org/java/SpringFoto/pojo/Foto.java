package org.java.SpringFoto.pojo;

import java.util.Arrays;
import java.util.List;

import org.java.SpringFoto.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "il titolo non può essere vuoto")
	private String titolo;
	@NotBlank(message = "la descrizione non può essere vuota")
	private String descrizione;
	@NotBlank(message = "l'url non può essere vuoto")
	private String url;
	private Boolean visibile;
	
	@ManyToMany
	private List<Categoria> categorie;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private User user;
	
	public Foto() {}
	public Foto(String titolo, String descrizione, String url, Boolean visibile, User user, Categoria...categorie) {
		
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
		
		setUser(user);
		
		setCategorie(categorie);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}
	
	@JsonSetter
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	public void setCategorie(Categoria[] categorie) {
		setCategorie(Arrays.asList(categorie));
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
