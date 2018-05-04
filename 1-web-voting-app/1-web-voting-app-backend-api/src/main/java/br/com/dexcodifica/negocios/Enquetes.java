package br.com.dexcodifica.negocios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.persistencia.EnqueteRepositorio;

@Service
public class Enquetes {

	private EnqueteRepositorio repositorio;

	@Autowired
	public Enquetes(EnqueteRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public void delete(Enquete enquete) {
		repositorio.delete(enquete);
	}

	public void delete(Long id) {
		repositorio.delete(id);
	}

	public boolean existe(Long id) {
		return repositorio.exists(id);
	}

	public List<Enquete> todos() {
		return repositorio.findAll();
	}

	public List<Enquete> todos(Iterable<Long> enquete) {
		return repositorio.findAll(enquete);
	}

	public Page<Enquete> todos(Pageable enquete) {
		return repositorio.findAll(enquete);
	}

	public <S extends Enquete> S umCom(Example<S> enquete) {
		return repositorio.findOne(enquete);
	}

	public Enquete umComId(Long id) {
		return repositorio.findOne(id);
	}

	public Enquete comId(Long id) {
		return repositorio.getOne(id);
	}

	public Optional<Enquete> comIdPublico(String idPublico) {
		return repositorio.findByIdPublico(idPublico);
	}
	
	public <S extends Enquete> S salvar(S enquete) {
		return repositorio.save(enquete);
	}
	
}
