package br.ufg.inf.es.avaliadocente.main;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.avaliadocente.context.CustomApplicationContext;
import br.ufg.inf.es.avaliadocente.main.generator.AvaliacoesJsonGenerator;
import br.ufg.inf.es.avaliadocente.main.populador.PopuladorBancoDeDados;

/**
 * Classe para inicialização do sistema em modo back-ground.
 * 
 * <p>
 * Ao iniciar, será carregado o {@code applicationContext.xml} do Spring Framework.
 * </p>
 * 
 * @author Danilo Guimarães
 *
 */
public class AvaliaDocenteMain {
	
	private static final Logger LOG = Logger.getLogger(AvaliaDocenteMain.class);
	
	@Autowired
	private static PopuladorBancoDeDados populador;
	
	@Autowired
	private static AvaliacoesJsonGenerator avaliacoesJsonGenerator;
	
	public static void main(String[] args) {
		LOG.info("Iniciando a aplicacao...");
		
		populador = CustomApplicationContext.getInstance().getContext().getBean(PopuladorBancoDeDados.class);
		avaliacoesJsonGenerator = CustomApplicationContext.getInstance().getContext().getBean(AvaliacoesJsonGenerator.class);
		
		//Aciona uma classe para popular alguns dados no banco...
		populador.popular();
		
		avaliacoesJsonGenerator.generate();
		
		LOG.info("Aplicacao iniciada.");
	}

}
