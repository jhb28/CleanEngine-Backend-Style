package net.junhabaek.springtestexercise.order.presentation;

import lombok.RequiredArgsConstructor;
import net.junhabaek.springtestexercise.common.response.ApiResponse;
import net.junhabaek.springtestexercise.order.application.BuyOrderCommand;
import net.junhabaek.springtestexercise.order.application.BuyOrderInfo;
import net.junhabaek.springtestexercise.order.application.BuyOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/buy-order")
@RequiredArgsConstructor
public class BuyOrderController {
    private final BuyOrderService buyOrderService;

    @PostMapping
    public ResponseEntity<ApiResponse<BuyOrderResponseDto.CreateBuyOrder>> createBuyOrder(
            @RequestBody BuyOrderRequestDto.CreateBuyOrderRequest createBuyOrderRequest) {

        LocalDateTime createdAt = LocalDateTime.now();
        BuyOrderCommand.CreateBuyOrder createBuyOrderCommand = createBuyOrderRequest.toBuyOrderCommand(createdAt);
        BuyOrderInfo buyOrderInfo = buyOrderService.createMarketBuyOrder(createBuyOrderCommand);

        return ApiResponse.success(BuyOrderResponseDto.CreateBuyOrder.from(buyOrderInfo), HttpStatus.CREATED)
                .toResponseEntity();
    }
}
