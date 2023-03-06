package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


	
@Entity
@Table(name="photos")
public class Foto {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		private String titolo;

		private String descrizione;

		private String url;

		private String tag;

		private Boolean visibilità;

		@OneToMany(mappedBy = "foto", cascade = CascadeType.ALL)
		private List<Commenti> commenti;

		@ManyToMany
		private List<Category> categorie;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
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

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public Boolean getVisibilità() {
			return visibilità;
		}

		public void setVisibilità(Boolean visibilità) {
			this.visibilità = visibilità;
		}

		public List<Commenti> getCommenti() {
			return commenti;
		}

		public void setCommenti(List<Commenti> commenti) {
			this.commenti = commenti;
		}

		public List<Category> getCategorie() {
			return categorie;
		}

		public void setCategorie(List<Category> categorie) {
			this.categorie = categorie;
		}

		
	
		
		
	
}
