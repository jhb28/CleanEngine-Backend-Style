package net.junhabaek.springtestexercise.order.application;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.junhabaek.springtestexercise.order.domain.BuyOrder;
import net.junhabaek.springtestexercise.order.infra.BuyOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class BuyOrderService {
    private final BuyOrderRepository buyOrderRepository;

    @Transactional
    public BuyOrderInfo createMarketBuyOrder(@Valid BuyOrderCommand.CreateBuyOrder createBuyOrder){
        BuyOrder buyOrder = BuyOrder.create(createBuyOrder.ticker(), createBuyOrder.userId(),
                createBuyOrder.size(), createBuyOrder.price(), false);

        buyOrderRepository.save(buyOrder);

        BuyOrderInfo buyOrderInfo = BuyOrderInfo.builder()
                .id(buyOrder.getId())
                .createdAt(buyOrder.getCreatedAt()).build();

        return buyOrderInfo;
    }

    @Transactional
    public BuyOrderInfo createLimitBuyOrder(@Valid BuyOrderCommand.CreateBuyOrder createBuyOrder){
        // 다른 validation 필요

        BuyOrder buyOrder = BuyOrder.create(createBuyOrder.ticker(), createBuyOrder.userId(),
                createBuyOrder.size(), createBuyOrder.price(), true);

        buyOrderRepository.save(buyOrder);

        BuyOrderInfo buyOrderInfo = BuyOrderInfo.builder()
                .id(buyOrder.getId())
                .createdAt(buyOrder.getCreatedAt()).build();

        return buyOrderInfo;
    }
}
