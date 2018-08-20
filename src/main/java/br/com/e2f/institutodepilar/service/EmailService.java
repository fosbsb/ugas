package br.com.e2f.institutodepilar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import br.com.e2f.institutodepilar.vo.EmailVO;

@Service
public class EmailService {

	private JavaMailSender mailSender;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public Boolean enviarEmail(EmailVO email) {
		email.setOrigem("servicos@institutodepilar.com");

		try {
			MimeMessagePreparator messagePreparator = mimeMessage -> {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				
				messageHelper.setFrom(email.getOrigem());
				messageHelper.setTo(email.getDestino());
				messageHelper.setSubject(email.getAssunto());
				
				String content = mailContentBuilder.build(email);
				messageHelper.setText(content, true);

			};

			mailSender.send(messagePreparator);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
