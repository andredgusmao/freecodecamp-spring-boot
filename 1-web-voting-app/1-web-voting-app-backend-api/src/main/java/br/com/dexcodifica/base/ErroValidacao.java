package br.com.dexcodifica.base;

public class ErroValidacao {

	private String mensagem;
	private String classe;
	private String propriedade;

	public ErroValidacao(String mensagem, String classe, String propriedade) {
		this.mensagem = mensagem;
		this.classe = classe;
		this.propriedade = propriedade;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}
	
}
