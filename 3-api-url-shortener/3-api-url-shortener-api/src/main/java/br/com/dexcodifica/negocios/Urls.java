package br.com.dexcodifica.negocios;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dexcodifica.acessorios.Validador;
import br.com.dexcodifica.modelo.Url;
import br.com.dexcodifica.persistencia.UrlRepositorio;

@Service
public class Urls {
	@Autowired
	private Logger log;
	
	@Autowired
	private UrlRepositorio repositorio;
	
	public boolean valida(String url) {
		log.info("Validando URL: {}", url);
		return Validador.url(url);
	}
	
	public Url comUrlEncurtada(String encurtada) {
		log.info("Id recebido: {}", encurtada);
		return repositorio.findByEncurtadaId(encurtada);
	}
	
	public Url salvar(String link) {
		Url url = new Url(link);	
		String shortUrl = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/{short}").replaceQuery("")
						.buildAndExpand(url.getEncurtadaId()).toUriString();
		url.setEncurtada(shortUrl);
		log.info("Url: {}; Url Encurtada: {}", url.getOriginal(), url.getEncurtada());
		return repositorio.save(url);
	}
	
	public String extraiLinkDoRequest(HttpServletRequest request) {
		ServletUriComponentsBuilder uri = ServletUriComponentsBuilder.fromCurrentRequest();		
        String path = uri.build().getPath();
        String query = uri.build().getQuery();;
        String link = path.replace("/novo/", "").concat("?").concat(query);
        link = link.replaceAll(":/", "://");
        
	    return link;
	}
}
