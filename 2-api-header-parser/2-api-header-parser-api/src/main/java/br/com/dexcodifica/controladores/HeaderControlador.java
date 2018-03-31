package br.com.dexcodifica.controladores;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeaderControlador {

	private Logger log = LoggerFactory.getLogger(HeaderControlador.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET}, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public ResponseEntity<String> endpoint(@RequestHeader("User-Agent") String userAgent,
            @RequestHeader("Accept-Language") String acceptLanguage, @RequestHeader HttpHeaders headers) {
		log.info("Valores recebidos no header: {}; {}", userAgent, acceptLanguage);
		log.info("IP do cliente: {}", request.getRemoteAddr());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}