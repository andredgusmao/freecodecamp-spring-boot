package br.com.dexcodifica.voto;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dexcodifica.base.ValidacaoException;
import br.com.dexcodifica.modelo.Voto;
import br.com.dexcodifica.repositorio.VotoRepositorio;

@Service
public class Votos {

	@Autowired
	private VotoRepositorio repositorio;

	public Voto salvar(Voto voto) throws ValidacaoException {
		try {
			return repositorio.saveAndFlush(voto);
		} catch (ConstraintViolationException ex) {
			throw new ValidacaoException(ex.getMessage(), ex);
		}
	}

	public Voto atualizar(Voto voto) throws ValidacaoException {
		try {
			return repositorio.saveAndFlush(voto);
		} catch (ConstraintViolationException ex) {
			throw new ValidacaoException(ex.getMessage(), ex);
		}
	}

	public Optional<Voto> existente(String enquete, Long usuario) {
		return repositorio.existente(enquete, usuario);
	}

	public List<Voto> todos() {
		return repositorio.findAll();
	}

	public List<Voto> daEnquete(String idPublico) {
		return repositorio.daEnquete(idPublico);
	}
}
