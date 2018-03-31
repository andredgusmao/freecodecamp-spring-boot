package br.com.dexcodifica.modelo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timestamp {

	private final Logger log = LoggerFactory.getLogger(Timestamp.class);
	
	private Long unix;
	private LocalDate natural;


	public Timestamp() {
	}

	public Timestamp(String tempo) {
		if(!this.converteNumero(tempo)) {
			this.converteData(tempo);			
		}
	}

	private boolean converteNumero(String tempo) {
		try {
			this.unix = Long.parseLong(tempo);
			this.natural = LocalDateTime.ofInstant(Instant.ofEpochSecond(this.unix), TimeZone
			        .getDefault().toZoneId()).toLocalDate();
			return true;
		} catch (NumberFormatException e) {
			log.info("O valor recebido {} não é um número.", tempo);
		}
		return false;
	}
	
	private boolean converteData(String tempo) {
		try {
			this.natural = LocalDate.parse(tempo, DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
			this.unix = this.natural.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
			return true;
		} catch (DateTimeParseException e) {
			log.info("O valor recebido {} não é uma data em linguagem natural.", tempo);
		}
		return false;
	}

	public Long getUnix() {
		return unix;
	}

	public void setUnix(Long unix) {
		this.unix = unix;
	}

	public String getNatural() {
		if(Objects.isNull(this.natural)) {
			return null;
		}
		return this.natural.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
	}

	public void setNatural(LocalDate natural) {
		this.natural = natural;
	}


}
