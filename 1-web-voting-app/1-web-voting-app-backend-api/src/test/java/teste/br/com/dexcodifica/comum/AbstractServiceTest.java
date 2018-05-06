package teste.br.com.dexcodifica.comum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.modelo.Usuario;
import br.com.dexcodifica.modelo.Voto;


@Profile("test")
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = { ServiceConfiguration.class })
public abstract class AbstractServiceTest {
	
	@Autowired
	public TestEntityManager manager;
	
	private Usuario usuario;
	private Enquete enquete;
	
	@Before
	public void antes() {
		this.usuario = manager.persistAndFlush(novoUsuario());
		this.enquete = novaEnquete();
		this.enquete.setNome("Enquete teste 2");
		this.enquete.setUsuario(usuario);
		this.enquete = this.manager.persistAndFlush(enquete);
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Enquete getEnquete() {
		return enquete;
	}
	
	public Usuario novoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("André");
		usuario.setEmail("andre.fake@email.com");
		usuario.setSenha("123456");
		return usuario;
	}

	public List<Usuario> usuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		IntStream.rangeClosed(1, 10).forEach(numero -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Usuario " + numero);
			usuario.setId(new Long(numero));
			usuario.setSenha("123456");
			usuario.setEmail(String.format("usuario%s@email.com", numero));
			
			usuarios.add(usuario);
		});
		return usuarios;
	}
	
	public Enquete novaEnquete() {
		Enquete enquete = new Enquete();
		enquete.setNome("Domingo de manhã?");
		enquete.setOpcao1("Pesca e companhia");
		enquete.setOpcao2("Siga bem caminhoneiro");
		enquete.setUsuario(this.novoUsuario());
		return enquete;
	}
	
	public Enquete novaEnqueteSemUsuario() {
		Enquete enquete = new Enquete();
		enquete.setNome("Domingo de manhã?");
		enquete.setOpcao1("Pesca e companhia");
		enquete.setOpcao2("Siga bem caminhoneiro");
		return enquete;
	}
	
	public Voto novoVoto() {
		return new Voto(this.novoUsuario(), this.novaEnquete(), "Opcao X");
	}
	
	public Voto novoVoto(Usuario usuario, Enquete enquete) {
		return new Voto(usuario, enquete, "Opcao X");
	}

	public Voto novoVoto(Usuario usuario, Enquete enquete, String opcao) {
		return new Voto(usuario, enquete, opcao);
	}
}
