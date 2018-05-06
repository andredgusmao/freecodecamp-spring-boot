package br.com.dexcodifica.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.repositorio.EnqueteRepositorio;
import br.com.dexcodifica.validacao.anotacao.EnqueteUnica;

public class ValidaEnqueteUnica implements ConstraintValidator<EnqueteUnica, Enquete> {

	@Autowired
	private EnqueteRepositorio repositorio;

	public ValidaEnqueteUnica(EnqueteRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	@Override
	public void initialize(EnqueteUnica anotacao) {
		
	}

	@Override
	public boolean isValid(Enquete enquete, ConstraintValidatorContext context) {
		System.out.println(enquete);
		System.out.println(repositorio);
		Example<Enquete> example = Example.of(enquete);
		return repositorio.exists(example);
	}

}
