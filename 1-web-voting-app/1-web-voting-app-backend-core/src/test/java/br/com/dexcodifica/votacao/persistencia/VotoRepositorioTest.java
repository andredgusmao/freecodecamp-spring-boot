package br.com.dexcodifica.votacao.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dexcodifica.Aplicacao;
import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.modelo.Usuario;
import br.com.dexcodifica.modelo.Voto;
import br.com.dexcodifica.persistencia.VotoRepositorio;
import br.com.dexcodifica.util.Data;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = Replace.NONE)
@ContextConfiguration(classes = Aplicacao.class)
public class VotoRepositorioTest {

	@Autowired
	private TestEntityManager manager;
	
	@Autowired
	VotoRepositorio repository;
	
	@Before
	public void antes() {
		Usuario usuario = manager.persistAndFlush(Data.novoUsuario());
		Enquete enquete = Data.novaEnquete();
		enquete.setUsuario(usuario);
		manager.persistAndFlush(enquete);
	}
	
	@Test
	public void aoInserirDeveRetornarOK() {
		Usuario usuario = manager.find(Usuario.class, 1L);
		Enquete enquete = manager.find(Enquete.class, 1L);
		
		Voto voto = Data.novoVoto(usuario, enquete);
		repository.save(voto);
		
		assertThat(voto.getId()).isEqualTo(1);
	}
	
	@Test
	public void existeVotoNaEnqueteParaUsuario() {
		Usuario usuario = manager.find(Usuario.class, 1L);
		Enquete enquete = manager.find(Enquete.class, 1L);
		Voto voto = Data.novoVoto(usuario, enquete);
		repository.save(voto);
		
		Optional<Voto> retorno = repository.existente("teste", 1L);
		assertThat(retorno.isPresent());
	}	
	
	@Test
	public void erroAoConsultarVotoNaoExistente() {
		Optional<Voto> voto = repository.existente("teste", Long.MAX_VALUE);
		assertThat(voto.isPresent());
	}	
	
}
