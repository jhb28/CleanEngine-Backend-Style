package net.junhabaek.springtestexercise.order.infra;

import net.junhabaek.springtestexercise.order.domain.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
}
