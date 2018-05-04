package br.com.dexcodifica.controladores;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	protected Logger log;
}
