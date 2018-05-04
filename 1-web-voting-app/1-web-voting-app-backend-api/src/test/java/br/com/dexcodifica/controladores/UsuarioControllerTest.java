package br.com.dexcodifica.controladores;

import static org.hamcrest.Matchers.hasSize;
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

import br.com.dexcodifica.negocios.Usuarios;
import br.com.dexcodifica.util.ConversorJson;
import br.com.dexcodifica.util.Data;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsuarioController.class)
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private Usuarios usuarios;
	
	@Before
	public void antesDoTeste() {
		when(usuarios.comId(1L)).thenReturn(Data.novoUsuario());		
		when(usuarios.todos()).thenReturn(Data.usuarios());
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
						.content(ConversorJson.objParaJson(Data.novoUsuario())));
		
		result.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
