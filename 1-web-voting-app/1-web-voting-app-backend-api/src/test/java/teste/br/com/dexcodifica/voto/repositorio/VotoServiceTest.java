package teste.br.com.dexcodifica.voto.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dexcodifica.base.ValidacaoException;
import br.com.dexcodifica.modelo.Voto;
import br.com.dexcodifica.voto.Votos;
import teste.br.com.dexcodifica.comum.AbstractServiceTest;

@RunWith(SpringRunner.class)
public class VotoServiceTest extends AbstractServiceTest {

	@Autowired
	private Votos votos;

	@Test
	public void deve_conter_erros_de_validacao() throws Exception {
		try {
			Voto voto = new Voto(null, null, null);
			voto = votos.salvar(voto);
		} catch (ValidacaoException ex) {
			assertThat(ex.getErrosValidacao()).isNotEmpty();
		}
	}

	@Test(expected = ValidacaoException.class)
	public void nao_deve_votar_sem_enquete() throws Exception {
		Voto voto = novoVoto(this.getUsuario(), null);
		voto = votos.salvar(voto);
	}

	@Test(expected = ValidacaoException.class)
	public void nao_deve_votar_sem_usuario() throws Exception {
		Voto voto = novoVoto(null, this.getEnquete());
		voto = votos.salvar(voto);
	}

	@Test(expected = ValidacaoException.class)
	public void nao_deve_votar_sem_opcao() throws Exception {
		Voto voto = novoVoto(this.getUsuario(), this.getEnquete(), "");
		votos.salvar(voto);
	}

	@Test
	public void deve_salvar_o_voto() throws Exception {
		Voto voto = novoVoto(this.getUsuario(), this.getEnquete());
		voto = votos.salvar(voto);
		assertThat(voto).isNotNull();
		assertThat(voto.getId()).isNotNull();
	}
}
