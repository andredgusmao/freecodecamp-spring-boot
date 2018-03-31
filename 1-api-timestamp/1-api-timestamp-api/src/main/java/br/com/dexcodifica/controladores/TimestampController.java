package br.com.dexcodifica.controladores;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.dexcodifica.modelo.Timestamp;;

@Controller
public class TimestampController {

	@RequestMapping(value = "/{tempo}", method = RequestMethod.GET, produces = { APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Timestamp> timestamp(@PathVariable String tempo) {
		Timestamp timestamp = new Timestamp(tempo);
		return new ResponseEntity<Timestamp>(timestamp, HttpStatus.OK);
	}
}
