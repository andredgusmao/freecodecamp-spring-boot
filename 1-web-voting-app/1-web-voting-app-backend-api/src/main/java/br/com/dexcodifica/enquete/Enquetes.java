package br.com.dexcodifica.enquete;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dexcodifica.base.ErroValidacao;
import br.com.dexcodifica.base.ValidacaoException;
import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.repositorio.EnqueteRepositorio;

@Service
public class Enquetes {

	@Autowired
	private EnqueteRepositorio repositorio;

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

	public <S extends Enquete> S salvar(S enquete) throws ValidacaoException {
		this.valida(enquete);
		try {
			return repositorio.save(enquete);
		} catch (ConstraintViolationException ex) {
			throw new ValidacaoException(ex.getMessage(), ex);
		}
	}

	private void valida(Enquete enquete) throws ValidacaoException {
		Example<Enquete> example = Example.of(enquete);
		if(repositorio.exists(example)) {
			ValidacaoException exception = new ValidacaoException("Erro de validacao");
			exception.addErro(new ErroValidacao("A Enquete ja existe", Enquete.class.getSimpleName(), ""));
			throw new ValidacaoException();
		}
	}
}
