package br.ufg.inf.es.avaliadocente.util;

import java.util.Date;

/**
 * Solução porca para medir o tempo de execução de um método.
 * 
 * <p>
 * Foi adotado assim devido a falta de tempo para implementar
 * algo melhor.
 * </p>
 * 
 * @author Danilo Guimarães
 *
 */
public class MethodProfiling {
	
	private Date dataInicio;
	private Date dataTermino;
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void iniciarMedicao() {
		dataInicio = new Date();
	}
	
	public void finalizarMedicao() {
		dataTermino = new Date();
	}
	
	public Double tempoExecucao() {
		return new Double(0.001*(dataTermino.getTime() - dataInicio.getTime()));
	}

}
