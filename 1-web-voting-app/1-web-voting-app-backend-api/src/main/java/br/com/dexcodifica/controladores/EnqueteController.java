package br.com.dexcodifica.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dexcodifica.modelo.Enquete;
import br.com.dexcodifica.negocios.Enquetes;

@RestController
@RequestMapping("/api/enquetes")
public class EnqueteController extends BaseController {

	private Enquetes enquetes;
	
	@Autowired
	public EnqueteController(Enquetes enquetes) {
		this.enquetes = enquetes;
	}
	
	@GetMapping("/{id}")
	public Enquete comId(@PathVariable Long id) {
		return enquetes.comId(id);
	}
	
	@GetMapping("/publico/{id}")
	public Optional<Enquete> comIdPublico(String id) {
		return enquetes.comIdPublico(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void nova(@RequestBody Enquete enquete) {
		enquetes.salvar(enquete);
	}
}
