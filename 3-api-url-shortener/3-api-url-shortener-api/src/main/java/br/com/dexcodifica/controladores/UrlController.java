package br.com.dexcodifica.controladores;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dexcodifica.modelo.Url;
import br.com.dexcodifica.negocios.Urls;

@Controller
public class UrlController {

	@Autowired
	private Logger log;

	@Autowired
	private Urls urls;

	@RequestMapping(value = "/novo/**", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Url> endpoint2(HttpServletRequest request) {
		String link = urls.extraiLinkDoRequest(request);
		if (urls.valida(link)) {
			Url url = urls.salvar(link);
			return new ResponseEntity<Url>(url, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{urlEncurtada}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String endpoint(@PathVariable String urlEncurtada) {
		Url url = urls.comUrlEncurtada(urlEncurtada);
		log.info(url.toString());
		return "redirect:" + url.getOriginal();
	}
}
