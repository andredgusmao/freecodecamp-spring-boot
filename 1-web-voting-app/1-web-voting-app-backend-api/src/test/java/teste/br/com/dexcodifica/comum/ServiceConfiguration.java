package teste.br.com.dexcodifica.comum;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.dexcodifica.enquete.Enquetes;
import br.com.dexcodifica.voto.Votos;

@Configuration
@ComponentScan("br.com.dexcodifica")
public class ServiceConfiguration {

	@Bean
	public Votos votos() {
		return new Votos();
	}

	@Bean
	public Enquetes enquetes() {
		return new Enquetes();
	}
	
	@Bean
	public Logger logger() {
		return Mockito.mock(Logger.class);
	}
}
