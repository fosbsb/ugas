package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Cliente;
import br.com.e2f.institutodepilar.repository.ClienteRepository;
import br.com.e2f.institutodepilar.vo.EmailVO;

@Service
@Transactional(readOnly = true)
public class ClienteService {

	@Autowired
	private ClienteRepository clienterepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	public Page<Cliente> recuperarClientePaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return clienterepository.findAll(pageRequest);
	}

	public Cliente recuperarCliente(Long id) {
		return clienterepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Cliente incluirCliente(@Valid Cliente cliente) {

		return clienterepository.save(cliente);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Cliente atualizarCliente(@Valid Cliente cliente) {
		return clienterepository.save(cliente);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void excluirCliente(Long id) {
		if (clienterepository.exists(id)) {
			clienterepository.delete(id);
		}
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
