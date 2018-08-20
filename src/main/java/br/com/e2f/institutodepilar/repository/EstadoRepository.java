package br.com.e2f.institutodepilar.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.e2f.institutodepilar.entity.Estado;

@SuppressWarnings("rawtypes")
@Repository
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long>, JpaSpecificationExecutor {
}