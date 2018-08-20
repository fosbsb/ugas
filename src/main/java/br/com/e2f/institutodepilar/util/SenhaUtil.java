package br.com.e2f.institutodepilar.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SenhaUtil {

	private final static String ENCODING_PADRAO="UTF-8";
	
	/**
	 * Construtor privado.
	 */
	private SenhaUtil() {
	}

	/**
	 * Algoritmo de hash de senhas.
	 */
	public enum Algoritmo {

		/**
		 * Algoritmo padrão do 1.x (verificação de senhas)
		 */
		SHA("SHA"),
		/**
		 * Algoritmo usado no módulo de segurança
		 */
		SHA256("SHA-256");

		private String nome;

		private Algoritmo(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}
	}

	/**
	 * @param valor
	 *            - valor utilizado para gerar o hash.
	 * @param frase
	 *            - frase que será combinada com o valor.
	 * @return hash do valor + frase passados com algoritmo SHA.
	 */
	public static String gerarHash(String valor, String frase) {
		return SenhaUtil.gerarHash(valor, frase, Algoritmo.SHA);
	}

	/**
	 * @param valor
	 *            - valor utilizado para gerar o hash.
	 * @param algoritmo
	 *            - algorimo que será utilizado para gerar o hash
	 * @return hash do valor com algoritmo informado.
	 */
	public static String gerarHash(String valor, Algoritmo algoritmo) {
		return gerarHash(valor, null, algoritmo);
	}

	/**
	 * @param senha
	 *            - valor utilizado para gerar o hash.
	 * @param saltLogin
	 *            - frase que será combinada com o valor.
	 * @param algoritmo
	 *            - algorimo que será utilizado para gerar o hash
	 * @return hash do valor + frase passados.
	 */
	public static String gerarHash(String senha, String saltLogin, Algoritmo algoritmo) {

		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo.getNome());

			if (saltLogin != null) {
				md.update(saltLogin.getBytes(ENCODING_PADRAO));
				byte[] salt = md.digest();

				md.reset();
				md.update(senha.getBytes(ENCODING_PADRAO));
				md.update(salt);
			} else {
				md.update(senha.getBytes(ENCODING_PADRAO));
			}

			byte[] raw = md.digest();
			return new String(Base64.getEncoder().encode(raw), ENCODING_PADRAO);

		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
