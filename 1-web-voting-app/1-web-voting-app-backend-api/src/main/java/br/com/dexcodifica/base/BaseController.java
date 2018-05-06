package br.com.dexcodifica.base;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	protected Logger log;
}
