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
	
	public MethodProfiling() {
		//Impedindo um possivel NullPointerException
		dataInicio = new Date();
		dataTermino = new Date();
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	/**
	 * Inicializa a medição do tempo.
	 */
	public void iniciarMedicao() {
		dataInicio = new Date();
	}
	
	/**
	 * Finaliza a medição do tempo.
	 */
	public void finalizarMedicao() {
		dataTermino = new Date();
	}
	
	/**
	 * Retorna o tempo de execução em segundos.
	 * @return tempo de execução em segundos.
	 */
	public Double tempoExecucao() {
		return new Double(0.001*(dataTermino.getTime() - dataInicio.getTime()));
	}

}
