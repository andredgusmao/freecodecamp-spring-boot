package br.com.dexcodifica.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.dexcodifica.acessorios.Alfanumerico;

@Document(collection = "urls")
public class Url {
	private static final int TAMANHO_URL_ENCURTADA = 8;
	
	@Id 
	@JsonIgnore
	private String id;
	
	@JsonProperty(value = "url_original")
	private String original;
	
	@JsonIgnore
	private String encurtadaId;
	
	@JsonProperty(value = "url_encurtada")
	private String encurtada;
	
	public Url() {}
	
	public Url(String link) {
		this.original = link;
		this.encurtadaId = Alfanumerico.aleatorio(TAMANHO_URL_ENCURTADA);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getEncurtada() {
		return encurtada;
	}
	public void setEncurtada(String encurtada) {
		this.encurtada = encurtada;
	}

	public String getEncurtadaId() {
		return encurtadaId;
	}

	public void setEncurtadaId(String encurtadaId) {
		this.encurtadaId = encurtadaId;
	}
	@Override
	public String toString() {
		return String.format("Original: %s; Id: %s; Encurtada: %s", original, encurtadaId, encurtada);
	}
}
