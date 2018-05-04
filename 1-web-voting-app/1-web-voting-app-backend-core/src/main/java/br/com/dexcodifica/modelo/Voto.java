package br.com.dexcodifica.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "voto")
public class Voto implements Serializable {
	
	private static final long serialVersionUID = -7532689931532273061L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String opcao;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Enquete enquete;
	
	public Voto(Usuario usuario, Enquete enquete,  String opcao) {
		this.usuario = usuario;
		this.enquete = enquete;
		this.opcao = opcao;		
	}

	public String getOpcao() {
		return opcao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Enquete getEnquete() {
		return enquete;
	}

	public void setEnquete(Enquete enquete) {
		this.enquete = enquete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enquete == null) ? 0 : enquete.hashCode());
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
		Voto other = (Voto) obj;
		if (enquete == null) {
			if (other.enquete != null)
				return false;
		} else if (!enquete.equals(other.enquete))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
