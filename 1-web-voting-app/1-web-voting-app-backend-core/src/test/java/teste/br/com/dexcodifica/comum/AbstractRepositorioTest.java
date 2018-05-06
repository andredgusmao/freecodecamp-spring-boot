package teste.br.com.dexcodifica.comum;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import br.com.dexcodifica.ConfiguracaoTeste;
import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.modelo.Usuario;
import teste.br.com.dexcodifica.util.Data;


@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = ConfiguracaoTeste.class)
public abstract class AbstractRepositorioTest {
	
	@Autowired
	public TestEntityManager manager;	
	
	protected Data data;

	private Usuario usuario;

	private Enquete enquete;
	
	@Before
	public void antes() {
		this.data = new Data();
		
		this.usuario = manager.persistAndFlush(data.novoUsuario());
		this.enquete = data.novaEnquete();
		this.enquete.setUsuario(usuario);
		this.enquete = manager.persistAndFlush(enquete);
	}
	
	public Enquete getEnquete() {
		return enquete;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
