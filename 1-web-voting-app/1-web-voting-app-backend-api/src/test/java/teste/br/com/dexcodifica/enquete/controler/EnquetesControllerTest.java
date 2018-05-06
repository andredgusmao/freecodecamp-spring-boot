package teste.br.com.dexcodifica.enquete.controler;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.dexcodifica.enquete.EnqueteController;
import br.com.dexcodifica.enquete.Enquetes;
import br.com.dexcodifica.usuario.Usuarios;
import teste.br.com.dexcodifica.comum.AbstractControlerTest;
import teste.br.com.dexcodifica.comum.ConversorJson;

@RunWith(SpringRunner.class)
@WebMvcTest(EnqueteController.class)
public class EnquetesControllerTest extends AbstractControlerTest {
	
	@MockBean
	private Enquetes enquetes;
	
	@MockBean
	private Usuarios usuarios;
	
	@Before
	public void setup() {
		when(enquetes.comId(1L)).thenReturn(data.novaEnquete());
		when(usuarios.comId(1L)).thenReturn(data.novoUsuario());
	}
	
	@Test
	public void testGETEnquete() throws Exception {
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/api/enquetes/1")
					.accept(MediaType.APPLICATION_JSON_VALUE));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("nome", equalTo("Domingo de manhã?")));
	}

	@Test
	public void testePOSTNovaEnquete() throws Exception {
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/enquetes")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(ConversorJson.objParaJson(data.novaEnquete())));		
		result.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
