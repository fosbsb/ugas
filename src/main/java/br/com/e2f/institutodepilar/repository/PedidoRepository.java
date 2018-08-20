package br.com.e2f.institutodepilar.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.e2f.institutodepilar.entity.Pedido;

@SuppressWarnings("rawtypes")
@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long>, JpaSpecificationExecutor {
}