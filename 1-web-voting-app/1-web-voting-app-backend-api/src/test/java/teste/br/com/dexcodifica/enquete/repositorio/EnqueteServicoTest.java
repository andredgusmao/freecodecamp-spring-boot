package teste.br.com.dexcodifica.enquete.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dexcodifica.base.ValidacaoException;
import br.com.dexcodifica.enquete.Enquetes;
import br.com.dexcodifica.modelo.Enquete;
import teste.br.com.dexcodifica.comum.AbstractServiceTest;

@RunWith(SpringRunner.class)
public class EnqueteServicoTest extends AbstractServiceTest {

	@Autowired
	private Enquetes enquetes;
	private Enquete enquete;
	private Enquete enqueteSemUsuario;

	@Before
	public void antesDosMetodos() {
		enquete = novaEnquete();
		enqueteSemUsuario = novaEnqueteSemUsuario();
	}

	@Test
	public void deve_criar_enquete_para_um_usuario() throws Exception {
		enqueteSemUsuario.setUsuario(this.getUsuario());
		enqueteSemUsuario = enquetes.salvar(enqueteSemUsuario);
		assertThat(enqueteSemUsuario).isNotNull();
		assertThat(enqueteSemUsuario.getId()).isNotNull();
	}

	@Test
	public void deve_gerar_id_publico_para_enquete_ao_salvar() throws Exception {
		enqueteSemUsuario.setUsuario(this.getUsuario());
		enqueteSemUsuario = enquetes.salvar(enqueteSemUsuario);
		assertThat(enqueteSemUsuario).isNotNull();
		assertThat(enqueteSemUsuario.getIdPublico()).isNotNull();
	}

	@Test(expected = ValidacaoException.class)
	public void nao_deve_criar_enquete_sem_usuario_vinculado() throws Exception {
		enqueteSemUsuario = novaEnqueteSemUsuario();
		enqueteSemUsuario = enquetes.salvar(enqueteSemUsuario);
	}

	@Test(expected = ValidacaoException.class)
	public void nao_deve_criar_enquete_sem_opcoes() throws Exception {
		enquete.setOpcao1("");
		enquete.setOpcao2("");
		enquete.setUsuario(this.getUsuario());
		enquetes.salvar(enquete);
	}

	@Test
	public void nao_deve_criar_enquete_repetida_para_usuario() throws Exception {
		enqueteSemUsuario.setUsuario(this.getUsuario());
		enqueteSemUsuario = enquetes.salvar(enqueteSemUsuario);
		assertThat(enqueteSemUsuario).isNotNull();
		assertThat(enqueteSemUsuario.getId()).isNotNull();
		assertThat(enqueteSemUsuario.getIdPublico()).isNotNull();

		try {
			Enquete enquete = novaEnqueteSemUsuario();
			enquete.setUsuario(this.getUsuario());
			enquete = enquetes.salvar(enquete);
		} catch (ValidacaoException ex) {
			assertThat(ex).isNotNull();
			assertThat(ex.getErrosValidacao()).isNotEmpty();
			assertThat(ex.getErrosValidacao().iterator().next().getMensagem()).isEqualTo("A Enquete ja existe");
		}
	}
}
