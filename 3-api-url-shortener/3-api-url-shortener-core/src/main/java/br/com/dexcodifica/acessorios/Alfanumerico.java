package br.com.dexcodifica.acessorios;

import java.security.SecureRandom;

public class Alfanumerico {

	private static final char[] caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	public static String aleatorio(int tamanho) {
	    SecureRandom random = new SecureRandom();	    
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < tamanho; i++) {
	    	char caracterAleatorio = caracteres[random.nextInt(caracteres.length)];
			builder.append(caracterAleatorio);
	    }
	    return builder.toString();
	}
}
