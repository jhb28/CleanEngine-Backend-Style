package net.junhabaek.springtestexercise.order.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import net.junhabaek.springtestexercise.common.validation.ConstraintMessageTemplate;

import java.time.LocalDateTime;

public final class BuyOrderCommand {
    private BuyOrderCommand() {}

    public record CreateBuyOrder (
        @Size(min = 1, max = 10, message = "ticker" +ConstraintMessageTemplate.SIZE_MESSAGE_TEMPLATE)
        @NotNull(message = "ticker" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        String ticker,

        @NotNull(message = "userId" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        Long userId,

        @NotNull(message = "size" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        @Positive(message = "size" + ConstraintMessageTemplate.POSITIVE_MESSAGE_TEMPLATE)
        Double size,

        @NotNull(message = "price" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        @Positive(message = "price" + ConstraintMessageTemplate.POSITIVE_MESSAGE_TEMPLATE)
        Double price,

        @NotNull(message = "isMarketOrder" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        Boolean isMarketOrder,

        @NotNull(message = "createdAt" + ConstraintMessageTemplate.NOTNULL_MESSAGE_TEMPLATE)
        LocalDateTime createdAt
    ){
        @Builder
        public CreateBuyOrder {}
    }
}
