package teste.br.com.dexcodifica.usuarios.controladores;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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

import br.com.dexcodifica.usuario.UsuarioController;
import br.com.dexcodifica.usuario.Usuarios;
import teste.br.com.dexcodifica.comum.AbstractControlerTest;
import teste.br.com.dexcodifica.comum.ConversorJson;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest extends AbstractControlerTest {
	
	@MockBean
	private Usuarios usuarios;
	
	@Before
	public void antesDoTeste() {		
		when(usuarios.comId(1L)).thenReturn(data.novoUsuario());		
		when(usuarios.todos()).thenReturn(data.usuarios());
	}
	
	@Test
	public void testGETUsuario() throws Exception {
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/api/usuarios/1")
					.accept(MediaType.APPLICATION_JSON_VALUE));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("email", equalTo("andre.fake@email.com")));
	}

	@Test
	public void testGETTodosOsUsuarios() throws Exception {
		
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/api/usuarios")
				.accept(MediaType.APPLICATION_JSON_VALUE));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(10)));
	}
	
	@Test
	public void testPOSTNovoUsuario() throws Exception {
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/usuarios")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(ConversorJson.objParaJson(data.novoUsuario())));
		
		result.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
