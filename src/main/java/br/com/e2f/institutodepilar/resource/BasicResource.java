package br.com.e2f.institutodepilar.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.e2f.institutodepilar.util.Mensagem;
import br.com.e2f.institutodepilar.vo.RetornoVO;

/**
 * Possui métodos básicos que podem ser utilizados Resource Rest.
 * 
 * @author eric
 *
 */
public abstract class BasicResource {

	private static final String REGEX_SORT = " ";

	/**
	 * Monta um objeto do tipo Sort com a ordenação que será utilizada em
	 * pesquisas.
	 * 
	 * Cada elemento do array de parametros deve ser composto por pelo nome do
	 * atributo e o tipo da ordenação. Exemplo: nome asc
	 * 
	 * O tipo da ordenação é uma String que deve ser preenchida com asc ou desc.
	 * 
	 * @param sortParams
	 *            Array de String
	 * @return
	 */
	protected Sort buildSort(String[] sortParams) {
		List<Sort.Order> sortOrders = new ArrayList<Sort.Order>();

		if (null != sortParams) {
			for (int i = 0; i < sortParams.length; i++) {
				String[] sortFields = sortParams[i].split(REGEX_SORT);

				if (sortFields.length == 2) {
					sortOrders.add(new Sort.Order(Direction.fromString(sortFields[1].toUpperCase()), sortFields[0]));
				}
			}

		}

		return new Sort(sortOrders);
	}

	protected RetornoVO getRetornoVO(Object retorno, List<String> mensagens) {
		ResponseEntity<?> response;
		
		if (retorno instanceof ResponseEntity){
			response = (ResponseEntity<?>) retorno;	
		}else{
			response = new ResponseEntity<>(retorno,HttpStatus.OK);
		}

		switch (response.getStatusCode()) {
		case OK:
			mensagens.add(Mensagem.SUCESSO.toString());
			break;
		case NO_CONTENT:
			mensagens.add(Mensagem.NAO_ENCONTRADO.toString());
			break;
		case NOT_FOUND:
			mensagens.add(Mensagem.NAO_ENCONTRADO.toString());
			break;
		case UNAUTHORIZED:
			mensagens.add(Mensagem.NAO_AUTORIZADO.toString());
			break;
		case BAD_REQUEST:
			mensagens.add(Mensagem.ERRO_SERVIDOR.toString());
			break;
		case INTERNAL_SERVER_ERROR:
			mensagens.add(Mensagem.ERRO_SERVIDOR.toString());
			break;
		default:
			mensagens.add(Mensagem.ERRO_DESCONHECIDO.toString());

			break;
		}

		RetornoVO vo = new RetornoVO();
		vo.setMensagens(mensagens);
		vo.setResultado(response.getBody());
		vo.setHttpHeaders(response.getHeaders());
		vo.setHttpStatus(response.getStatusCode());

		return vo;
	}

	protected RetornoVO getRetornoVO(Object retorno) {
		return getRetornoVO(retorno, new ArrayList<String>());
	}

	protected RetornoVO getRetornoVO(Object retorno, String mensagem) {
		List<String> mensagens = new ArrayList<String>();
		mensagens.add(mensagem);
		return getRetornoVO(retorno, mensagens);
	}

}