package br.grupointegrado.lanches.repository;

import br.grupointegrado.lanches.model.Pedido;
import org.hibernate.query.criteria.JpaSearchedCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
