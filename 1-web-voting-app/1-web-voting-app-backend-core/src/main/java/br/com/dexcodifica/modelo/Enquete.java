package br.com.dexcodifica.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.dexcodifica.modelo.eventos.EnqueteEventos;


@Entity
@EntityListeners(value = { EnqueteEventos.class })
@Table(name = "enquete")
public class Enquete implements Serializable {

	private static final long serialVersionUID = -7824078924767245448L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String idPublico; 
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String opcao1;

	@NotEmpty
	private String opcao2;
	
	@NotNull
	@ManyToOne
	@JsonIgnore
	private Usuario usuario;
	
	@OneToMany(mappedBy = "enquete")
	@JsonIgnore
	private List<Voto> votos;

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

	public String getOpcao1() {
		return opcao1;
	}

	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}

	public String getOpcao2() {
		return opcao2;
	}

	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIdPublico() {
		return idPublico;
	}

	public void setIdPublico(String idPublico) {
		this.idPublico = idPublico;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Enquete other = (Enquete) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("[Nome: %s; Id: %s; Opcao1: %s; Opcao2: %s; usuario: %s]", nome, idPublico, opcao1, opcao2, usuario);
	}
}
