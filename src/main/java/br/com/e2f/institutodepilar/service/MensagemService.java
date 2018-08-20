package br.com.e2f.institutodepilar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.e2f.institutodepilar.entity.Mensagem;

@Service
public class MensagemService {
	
	@Autowired
	RestTemplate restTemplate;

	public Mensagem enviarPush(Mensagem mensagem) {
		
		String chave = "AIzaSyBfaLaRJX647VfVSSPITdHs491YFZb04qw";
		String url =  "https://fcm.googleapis.com/fcm/send";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "key="+chave);
		
		HttpEntity<Mensagem> entity = new HttpEntity<Mensagem>(mensagem, headers);
		String result = restTemplate.postForObject(url, entity, String.class);
		mensagem.setBodyMensagem(result);
		
		return mensagem;
	}

	
	
}
