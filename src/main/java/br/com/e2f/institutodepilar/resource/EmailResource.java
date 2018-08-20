package br.com.e2f.institutodepilar.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.institutodepilar.service.EmailService;
import br.com.e2f.institutodepilar.vo.EmailVO;
import br.com.e2f.institutodepilar.vo.RetornoVO;

@RestController
@RequestMapping("/api/email")
public class EmailResource extends BasicResource {

	@Autowired
	private EmailService emailService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO enviar(@RequestBody EmailVO email) {
		return getRetornoVO(emailService.enviarEmail(email));
	}

}