package br.com.dexcodifica.base;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = -5237576036945351239L;
	private Set<ErroValidacao> errosValidacao;

	public ValidacaoException() {}
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
		this.errosValidacao = new HashSet<>();
	}
	
	public ValidacaoException(String mensagem, ConstraintViolationException ex) {
		super(mensagem);
		this.errosValidacao = new HashSet<>();
		this.preencheErros(ex.getConstraintViolations());
	}
	
	private void preencheErros(Set<ConstraintViolation<?>> violacoes) {
		violacoes.forEach(v -> {
			errosValidacao.add(new ErroValidacao(v.getMessage(), v.getRootBeanClass().toString(), v.getLeafBean().toString()));
		});
	}
	
	public boolean addErro(ErroValidacao erro) {
		return this.errosValidacao.add(erro);
	}
	
	public Set<ErroValidacao> getErrosValidacao() {
		return errosValidacao;
	}
}
