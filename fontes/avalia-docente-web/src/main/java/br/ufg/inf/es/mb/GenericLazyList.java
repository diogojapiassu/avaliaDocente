package br.ufg.inf.es.mb;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.ufg.inf.es.avaliadocente.model.support.AbstractEntity;
import br.ufg.inf.es.avaliadocente.repository.support.GenericRepository;

/**
 * Implementação padrão do LazyDataModel.
 *
 * <p>
 * 	Extende recursos do LazyDataModel, afim de oferecer uma paginação "preguiçosa" para
 * a datatable, utilizando a paginação do Spring Data (Pageable).
 * </p>
 *
 * @author Douglas Japiassu
 *
 * @param <T> Que extende uma AbstractEntity
 */
public class GenericLazyList<T extends AbstractEntity<T>> extends LazyDataModel<T> {

    private static final long serialVersionUID = 1L;

    private List<T> lista;
    public GenericRepository<T, Long> genericRepository;
    private String nomeSort;

    public GenericLazyList(GenericRepository<T, Long> repository, String nome) {
		this.genericRepository = (GenericRepository<T, Long>) repository;
		this.nomeSort = nome;
	}

    @Override
    public List<T> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    	Page<T> pagina = genericRepository.findAll(getPageable(startingAt, maxPerPage, this.nomeSort));
    	lista = pagina.getContent();

        // set the total of players
        if(getRowCount() <= 0){
            setRowCount((int) pagina.getTotalElements());
        }

        // set the page dize
        setPageSize(maxPerPage);

        return lista;
    }

    private Pageable getPageable(int startingAt, int maxPerPage, String nomeAtributo) {
    	int page = 0;

    	if (startingAt > maxPerPage) {
    		page = startingAt / maxPerPage;
    	}

    	return new PageRequest(page, maxPerPage, new Sort(Direction.DESC, nomeAtributo));
    }

    @Override
    public Object getRowKey(T tabela) {
        return tabela.getId();
    }

    @Override
    public T getRowData(String quadroSumarioId) {
        Integer id = Integer.valueOf(quadroSumarioId);

        for (T tabela : lista) {
            if(id.equals(tabela.getId())){
                return tabela;
            }
        }

        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
           if (getPageSize() == 0) {
                rowIndex = -1;
            }
            super.setRowIndex(rowIndex);
    }
}