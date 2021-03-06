package br.ufg.inf.es.avaliadocente.repository;

import java.util.List;

import br.ufg.inf.es.avaliadocente.model.bean.Departamento;
import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link Docente}.
 * 
 * @author Danilo Guimarães
 *
 */
public interface DocenteRepository extends GenericRepository<Docente, Long> {

	/**
	 * Procura os docentes pelo nome.
	 * 
	 * @param nome nome do docente.
	 * @return lista de {@link Docente}s.
	 */
	List<Docente> findByNome(String nome);
	
	/**
	 * Procura os docentes pelo departamento
	 * @param departamento nome do departamento
	 * @return lista de {@link Docente}s.
	 */
	List<Docente> findByDepartamento(String departamento);
	
	/**
	 * Procura um docente combinando o nome e a matrícula.
	 * @param nome
	 * @param matricula
	 * @return
	 */
	Docente findByNomeAndMatricula(String nome, String matricula);
	
	/**
	 * Procura um docente combinando o nome, matrícula e departamento.
	 * @param nome
	 * @param matricula
	 * @param departamento
	 * @return
	 */
	Docente findByNomeAndMatriculaAndDepartamento(String nome, String matricula, Departamento departamento);
}
