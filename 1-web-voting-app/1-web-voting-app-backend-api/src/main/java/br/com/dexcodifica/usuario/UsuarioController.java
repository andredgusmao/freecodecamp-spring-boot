package br.com.dexcodifica.usuario;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.dexcodifica.base.BaseController;
import br.com.dexcodifica.modelo.Usuario;

@Controller
@RequestMapping(path = "/api/usuarios")
public class UsuarioController extends BaseController {

	private Usuarios usuarios;
	
	@Autowired
	public UsuarioController(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Usuario comId(@PathVariable Long id) {
		return usuarios.comId(id);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Usuario> todos() {
		return usuarios.todos();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void nova(@RequestBody Usuario usuario) {
		usuarios.salvar(usuario);
	}
	
	@PostMapping("/login")
	public void login(@RequestBody Usuario usuario) throws Exception {
		usuario.setSenha(Base64.getEncoder().encodeToString(usuario.getSenha().getBytes()));
		Example<Usuario> exemplo = Example.of(usuario);
		Usuario login = usuarios.comExemplo(exemplo);
		if(Objects.isNull(login)) throw new Exception();
	}
}
