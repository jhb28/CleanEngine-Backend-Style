package net.junhabaek.springtestexercise.order.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.junhabaek.springtestexercise.order.application.BuyOrderInfo;

import java.time.LocalDateTime;

public final class BuyOrderResponseDto {
    private BuyOrderResponseDto() {}

    @JsonPropertyOrder({"orderId", "createdAt"})
    public record CreateBuyOrder(
            @JsonProperty("orderId")
            Long id,
            @JsonProperty("createdAt")
            LocalDateTime createdAt
    ) {
        public static CreateBuyOrder from(BuyOrderInfo buyOrderInfo) {
            return new CreateBuyOrder(buyOrderInfo.id(), buyOrderInfo.createdAt());
        }
    }
}
