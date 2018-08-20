package br.com.e2f.institutodepilar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.e2f.institutodepilar.entity.Cidade;

@SuppressWarnings("rawtypes")
@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long>, JpaSpecificationExecutor {

	@Query("select c from Cidade c where c.estado.id =?1")
	List<Cidade> getCidadesPorEstado(Long idestado);
}
