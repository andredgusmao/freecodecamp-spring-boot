package teste.br.com.dexcodifica.votacao.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.modelo.Usuario;
import br.com.dexcodifica.modelo.Voto;
import br.com.dexcodifica.repositorio.VotoRepositorio;
import teste.br.com.dexcodifica.comum.AbstractRepositorioTest;

@RunWith(SpringRunner.class)
public class VotoRepositorioTest extends AbstractRepositorioTest {

	@Autowired
	VotoRepositorio repository;	
	
	@Test
	public void aoInserirDeveRetornarOK() {
		Usuario usuario = manager.find(Usuario.class, 1L);
		Enquete enquete = manager.find(Enquete.class, 1L);
		
		Voto voto = data.novoVoto(usuario, enquete);
		repository.save(voto);
		
		assertThat(voto.getId()).isEqualTo(1);
	}
	
	@Test
	public void existeVotoNaEnqueteParaUsuario() {
		Voto voto = data.novoVoto(getUsuario(), getEnquete());
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
