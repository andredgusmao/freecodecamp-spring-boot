package br.com.dexcodifica.modelo.eventos;

import java.util.Base64;
import java.util.Objects;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.dexcodifica.modelo.Usuario;

public class UsuarioEventos {

	@PrePersist
	@PreUpdate
	public void hashSenha(final Usuario usuario) {
		if(Objects.nonNull(usuario.getSenha())) {
			usuario.setSenha(Base64.getEncoder().encodeToString(usuario.getSenha().getBytes()));
		}
	}
}
