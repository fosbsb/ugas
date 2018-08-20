package br.com.e2f.institutodepilar.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.e2f.institutodepilar.entity.PedidoServicoItem;

@SuppressWarnings("rawtypes")
@Repository
public interface PedidoServicoItemRepository
		extends PagingAndSortingRepository<PedidoServicoItem, Long>, JpaSpecificationExecutor {
}