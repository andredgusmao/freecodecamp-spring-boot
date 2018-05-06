package teste.br.com.dexcodifica.comum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.modelo.Usuario;
import br.com.dexcodifica.modelo.Voto;

public class Data {

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
		enquete.setIdPublico("teste");
		enquete.setUsuario(this.novoUsuario());
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
