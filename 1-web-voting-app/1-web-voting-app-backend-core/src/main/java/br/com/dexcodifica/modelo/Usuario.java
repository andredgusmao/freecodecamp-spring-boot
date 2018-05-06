package br.com.dexcodifica.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.dexcodifica.modelo.eventos.UsuarioEventos;


@Entity
@EntityListeners(value = { UsuarioEventos.class })
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3083162659959866242L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	private String email;
	
	private String senha;
	
	@OneToMany(mappedBy = "usuario")
	private List<Enquete> enquetes;
	
	@OneToMany(mappedBy = "usuario")
	private List<Voto> votos;

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Enquete> getEnquetes() {
		return enquetes;
	}

	public void setEnquetes(List<Enquete> enquetes) {
		this.enquetes = enquetes;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("[Nome: %s; Email: %s]", this.nome, this.email);
	}
}
