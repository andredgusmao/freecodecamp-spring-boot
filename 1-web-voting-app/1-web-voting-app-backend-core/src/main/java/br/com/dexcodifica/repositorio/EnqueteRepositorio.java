package br.com.dexcodifica.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dexcodifica.modelo.Enquete;

@Repository
public interface EnqueteRepositorio extends JpaRepository<Enquete, Long> {

	public Optional<Enquete> findByIdPublico(String idPublico);

	@Query("SELECT e FROM Enquete e WHERE e.usuario.id = :id")
	public List<Enquete> findByIdUsuario(@Param("id") Long id);

	@Query("SELECT e FROM Enquete e WHERE e.usuario.email = :email")
	public List<Enquete> findByEmailUsuario(@Param("email") String email);
}
