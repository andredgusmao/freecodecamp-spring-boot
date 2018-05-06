package teste.br.com.dexcodifica.comum;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.dexcodifica.repositorio.EnqueteRepositorio;
import br.com.dexcodifica.repositorio.UsuarioRepositorio;
import br.com.dexcodifica.repositorio.VotoRepositorio;

@Configuration
@ComponentScan("br.com.dexcodifica")
public class ConfiguracaoControler {

	@Autowired
	private VotoRepositorio votoRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private EnqueteRepositorio enqueteRepositorio;
	
	@Bean
	public VotoRepositorio votoRepositorio() {
		return votoRepositorio;
	}
	
	@Bean
	public UsuarioRepositorio usuarioRepositorio() {
		return usuarioRepositorio;
	}
	
	@Bean
	public EnqueteRepositorio enqueteRepositorio() {
		return enqueteRepositorio;
	}
	
	@Bean
	public Logger logger() {
		return Mockito.mock(Logger.class);
	}
}
