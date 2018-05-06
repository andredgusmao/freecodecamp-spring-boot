package teste.br.com.dexcodifica.comum;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@Profile("test")
@ContextConfiguration(classes = { ConfiguracaoControler.class })
public abstract class AbstractControlerTest {

	@Autowired
	public MockMvc mockMvc;
	
	public Data data;
	
	@Before
	public void antes() {
		this.data = new Data();
	}
}
