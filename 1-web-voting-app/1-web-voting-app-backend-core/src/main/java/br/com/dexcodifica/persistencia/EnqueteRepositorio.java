package br.com.dexcodifica.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dexcodifica.modelo.Enquete;

@Repository
public interface EnqueteRepositorio extends JpaRepository<Enquete, Long> {

	public Optional<Enquete> findByIdPublico(String idPublico);
}
