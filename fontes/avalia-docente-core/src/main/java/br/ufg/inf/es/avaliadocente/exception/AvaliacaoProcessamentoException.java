package br.ufg.inf.es.avaliadocente.exception;

import br.ufg.inf.es.avaliadocente.model.bean.Avaliacao;

/**
 * Encapsula uma {@link RuntimeException} lançada durante o processamento de uma {@link Avaliacao}.
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliacaoProcessamentoException extends RuntimeException {

	private static final long serialVersionUID = 3780485593695724829L;
	
	public AvaliacaoProcessamentoException(String mensagem) {
		super(mensagem);
	}
	
	public AvaliacaoProcessamentoException(String mensagem, Exception e) {
		super(mensagem, e);
	}

}
