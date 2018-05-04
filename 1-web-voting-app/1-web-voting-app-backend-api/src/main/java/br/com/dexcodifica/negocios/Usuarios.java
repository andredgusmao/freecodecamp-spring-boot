package br.com.dexcodifica.negocios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dexcodifica.modelo.Usuario;
import br.com.dexcodifica.persistencia.UsuarioRepositorio;

@Service
public class Usuarios {

	private UsuarioRepositorio repositorio;

	@Autowired
	public Usuarios(UsuarioRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public void delete(Long id) {
		repositorio.delete(id);
	}

	public boolean existe(Long id) {
		return repositorio.exists(id);
	}

	public List<Usuario> todos() {
		return repositorio.findAll();
	}

	public Page<Usuario> todos(Pageable pagina) {
		return repositorio.findAll(pagina);
	}

	public <S extends Usuario> S comExemplo(Example<S> usuario) {
		return repositorio.findOne(usuario);
	}

	public Usuario comId(Long id) {
		return repositorio.findOne(id);
	}

	@Transactional
	public <S extends Usuario> S salvar(S usuario) {
		return repositorio.save(usuario);
	}
	
	
}
