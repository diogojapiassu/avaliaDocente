package br.ufg.inf.es.avaliadocente.repository;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;

import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.ufg.inf.es.avaliadocente.model.bean.Docente;
import br.ufg.inf.es.avaliadocente.model.bean.builder.DocenteBuilder;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

/**
 * Classe de testes unitários do componente {@link DocenteRepository}.
 * 
 * <p>
 * Utiliza o framework DbUnit para 'mockar' o banco dedados e comparar os resultados.
 * </p>
 *  
 * @author Danilo Guimarães
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContextTest-persistence.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocenteRepositoryTest {

	private final String INITIAL_DB = "/databases/docente/initial-db.xml";
	private final String SAVED_DB = "/databases/docente/saved-db.xml";
	private final String DELETED_DB = "/databases/docente/deleted-db.xml";
	private final String UPDATED_DB = "/databases/docente/updated-db.xml";

	@Autowired
	private DocenteRepository repository;

	/**
	 * Novo docente efetivado na UFG...
	 */
	@Test
	@DatabaseSetup(INITIAL_DB)
	@ExpectedDatabase(value = SAVED_DB, assertionMode = NON_STRICT)
	@Ignore
	public void A_testSave() {
		this.repository.save(this.getNovoDocente());
	}

	/**
	 * Retorna um novo docente para que seja inserido.
	 * 
	 * @return
	 */
	private Docente getNovoDocente() {
		return new DocenteBuilder().id(3L).nome("Ricardo Rocha").build();
	}

	/**
	 * O professor Marcelo resolveu largar a computação e foi pra Química...
	 */
//	@Test
	// TODO checar o porque esta esperando 4 ao inves de 3...
	@DatabaseSetup(SAVED_DB)
	@ExpectedDatabase(value = UPDATED_DB, assertionMode = NON_STRICT)
	public void B_testUpdate() {
		final Docente docente = new DocenteBuilder().id(1L)
				.nome("Marcelo Quinta").build();
		this.repository.save(docente);
	}

//	@Test
	// TODO o delete aparentemente nao esta deletando...
	@DatabaseSetup(SAVED_DB)
	@ExpectedDatabase(value = DELETED_DB, assertionMode = NON_STRICT)
	public void C_testDelete() {
		final Docente docente = this.getNovoDocente();
//		 docente.setId(3L);
		this.repository.delete(docente);
	}

	@Test
	@DatabaseSetup(INITIAL_DB)
	public void testFind() {
		final Docente result = this.repository.findOne(1L);
		final Matcher<Docente> mat1 = Matchers.allOf(
				Matchers.hasProperty("id", Matchers.is(1L)),
				Matchers.hasProperty("nome", Matchers.is("Marcelo Quinta")),
				Matchers.hasProperty("departamento",
						Matchers.is("Instituto de Informática")));
		Assert.assertThat(result, mat1);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup(INITIAL_DB)
	public void testFindAll() {
		final List<Docente> resultList = this.repository.findAll();
		Assert.assertThat(resultList.size(), Matchers.is(2));
		final Matcher<Docente> mat1 = Matchers.allOf(
				Matchers.hasProperty("id", Matchers.is(1L)),
				Matchers.hasProperty("nome", Matchers.is("Marcelo Quinta")),
				Matchers.hasProperty("departamento",
						Matchers.is("Instituto de Informática")));
		final Matcher<Docente> mat2 = Matchers.allOf(
				Matchers.hasProperty("id", Matchers.is(2L)),
				Matchers.hasProperty("nome", Matchers.is("João da Física")),
				Matchers.hasProperty("departamento",
						Matchers.is("Instituto de Física")));
		Assert.assertThat(resultList, Matchers.contains(mat1, mat2));
	}
	
	/**
	 * Testa a busca por docente com base no departamento.
	 * 
	 * <p>
	 * DETALHE: a acentuação correta é necessária!
	 * </p>
	 *  
	 */
	@Test
	@DatabaseSetup(SAVED_DB)
	public void testFindByDepartamento() {
		final List<Docente> resultList = this.repository.findByDepartamento("Instituto de Informática");
		Assert.assertEquals(resultList.size(), 2);
	}
	
	/**
	 * Testa a busca por docente com base no nome.
	 * 
	 * <p>
	 * DETALHE: a acentuação correta é necessária!
	 * </p>
	 *  
	 */
	@Test
	@DatabaseSetup(SAVED_DB)
	public void testFindByNome() {
		final List<Docente> resultList = this.repository.findByNome("Marcelo Quinta");
		Assert.assertEquals(resultList.size(), 1);
	}

}
