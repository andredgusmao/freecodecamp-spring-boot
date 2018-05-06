package br.com.dexcodifica.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dexcodifica.modelo.Voto;

@Repository
public interface VotoRepositorio extends JpaRepository<Voto, Long> {
	
	@Query("SELECT v FROM Voto v WHERE v.enquete.idPublico = :idPublico")
	public List<Voto> daEnquete(@Param("idPublico") String idPublico);
	
	@Query("SELECT v FROM Voto v WHERE v.enquete.idPublico = :enquete AND v.usuario.id = :usuario")
	public Optional<Voto> existente(@Param("enquete") String enquete, @Param("usuario") Long usuario);
}
