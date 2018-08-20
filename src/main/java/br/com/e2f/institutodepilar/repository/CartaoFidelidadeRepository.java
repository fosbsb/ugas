package br.com.e2f.institutodepilar.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.e2f.institutodepilar.entity.CartaoFidelidade;

@SuppressWarnings("rawtypes")
@Repository
public interface CartaoFidelidadeRepository
		extends PagingAndSortingRepository<CartaoFidelidade, Long>, JpaSpecificationExecutor {
}