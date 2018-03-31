package br.com.dexcodifica.persistencia;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dexcodifica.modelo.Url;

@Repository
public interface UrlRepositorio extends MongoRepository<Url, String> {

	public Url findByEncurtadaId(String urlEncurtada);
}
