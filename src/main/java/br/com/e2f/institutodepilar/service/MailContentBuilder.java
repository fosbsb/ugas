package br.com.e2f.institutodepilar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.e2f.institutodepilar.vo.EmailVO;

@Service
public class MailContentBuilder {

	private TemplateEngine templateEngine;
	
	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine){
		this.templateEngine = templateEngine;
	}
	
	public String build(EmailVO email){
		Context context = new Context();
		switch (email.getTemplate()) {
		case ESQUECI_SENHA:
			context.setVariable("titulo", email.getAssunto());
			context.setVariable("senha", email.getSenhaGerada());
			context.setVariable("nome", email.getNome());
			
			break;
		default:
			context.setVariable("message", email.getMensagem());
			
			break;
		}
		
		return templateEngine.process(email.getTemplate().getDescricao(), context);
	}

}
