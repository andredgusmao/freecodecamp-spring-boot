package br.com.dexcodifica.modelo.eventos;

import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.dexcodifica.modelo.Enquete;

public class EnqueteEventos {

	@PrePersist
	@PreUpdate
	public void geraIdPublico(Enquete enquete) {
		enquete.setIdPublico(UUID.randomUUID().toString());
	}
}
