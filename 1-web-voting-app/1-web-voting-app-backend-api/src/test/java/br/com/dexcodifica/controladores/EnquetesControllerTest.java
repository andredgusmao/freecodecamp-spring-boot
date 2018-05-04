package br.com.dexcodifica.controladores;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.dexcodifica.negocios.Enquetes;
import br.com.dexcodifica.negocios.Usuarios;
import br.com.dexcodifica.util.ConversorJson;
import br.com.dexcodifica.util.Data;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EnqueteController.class)
public class EnquetesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Enquetes enquetes;
	
	@MockBean
	private Usuarios usuarios;
	
	@Before
	public void setup() {
		when(enquetes.comId(1L)).thenReturn(Data.novaEnquete());
		when(usuarios.comId(1L)).thenReturn(Data.novoUsuario());
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
						.content(ConversorJson.objParaJson(Data.novaEnquete())));		
		result.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
