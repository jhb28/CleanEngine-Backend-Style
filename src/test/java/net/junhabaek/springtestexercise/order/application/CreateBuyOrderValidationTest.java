package net.junhabaek.springtestexercise.order.application;

import jakarta.validation.constraints.Size;
import net.junhabaek.springtestexercise.base.ValidatorTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class CreateBuyOrderValidationTest extends ValidatorTest<BuyOrderCommand.CreateBuyOrder> {
    public final static String VALID_TOKEN_NAME = "BTC";
    public final static Long VALID_USER_ID = 1L;
    public final static Double VALID_SIZE = 3.5;
    public final static Double VALID_PRICE = 55.5;
    public final static Boolean VALID_IS_MARKET_ORDER = true;
    public final static LocalDateTime VALID_CREATED_AT = LocalDateTime.now();

    @Test
    void Given_ValidCommand_When_ValidateIt_Then_NoneOfConstraintViolationReturned() {
        //given
        BuyOrderCommand.CreateBuyOrder validCreateBuyOrderCommand =
                BuyOrderCommand.CreateBuyOrder.builder()
                        .ticker(VALID_TOKEN_NAME)
                        .userId(VALID_USER_ID)
                        .size(VALID_SIZE)
                        .price(VALID_PRICE)
                        .isMarketOrder(VALID_IS_MARKET_ORDER)
                        .createdAt(VALID_CREATED_AT)
                        .build();

        //when
        List<ConstraintViolationInfo> constraintViolationInfoList = validate(validCreateBuyOrderCommand);

        //then
        then(constraintViolationInfoList.size()).isEqualTo(0);
    }

    /*
        START OF TICKER TEST
     */
    @Test
    void Given_EmptyTokenName_When_ValidatingCommand_Then_SizeConstraintViolationReturned() {
        //given
        String emptyTokenName = "";

        BuyOrderCommand.CreateBuyOrder inValidCreateBuyOrderCommand =
                BuyOrderCommand.CreateBuyOrder.builder()
                        .ticker(emptyTokenName)
                        .userId(VALID_USER_ID)
                        .size(VALID_SIZE)
                        .price(VALID_PRICE)
                        .isMarketOrder(VALID_IS_MARKET_ORDER)
                        .createdAt(VALID_CREATED_AT)
                        .build();

        //when
        List<ConstraintViolationInfo> constraintViolationInfoList = validate(inValidCreateBuyOrderCommand);

        //then
        then(constraintViolationInfoList.size()).isEqualTo(1);

        ConstraintViolationInfo bookNameConstraintViolation = constraintViolationInfoList.getFirst();
        then(bookNameConstraintViolation.getConstraintClass()).isEqualTo(Size.class);
        then(bookNameConstraintViolation.getInvalidValue()).isEqualTo(emptyTokenName);
        then(bookNameConstraintViolation.getMessage()).isEqualTo("ticker's length should be between 1 and 10. but, submitted value was ''.");
    }

    @Test
    void Given_LongTokenName_When_ValidatingCommand_Then_SizeConstraintViolationReturned() {
        //given
        String longTokenNameWith12Char = "BTCBTCBTCBTC";

        BuyOrderCommand.CreateBuyOrder inValidCreateBuyOrderCommand =
                BuyOrderCommand.CreateBuyOrder.builder()
                        .ticker(longTokenNameWith12Char)
                        .userId(VALID_USER_ID)
                        .size(VALID_SIZE)
                        .price(VALID_PRICE)
                        .isMarketOrder(VALID_IS_MARKET_ORDER)
                        .createdAt(VALID_CREATED_AT)
                        .build();

        //when
        List<ConstraintViolationInfo> constraintViolationInfoList = validate(inValidCreateBuyOrderCommand);

        //then
        then(constraintViolationInfoList.size()).isEqualTo(1);

        ConstraintViolationInfo bookNameConstraintViolation = constraintViolationInfoList.get(0);
        then(bookNameConstraintViolation.getConstraintClass()).isEqualTo(Size.class);
        then(bookNameConstraintViolation.getInvalidValue()).isEqualTo(longTokenNameWith12Char);
        then(bookNameConstraintViolation.getMessage()).isEqualTo("ticker's length should be between 1 and 10. but, submitted value was 'BTCBTCBTCBTC'.");
    }
    /*
        END OF TICKER TEST
     */

    /*
        START OF SIZE TEST
     */
    // @Test
    // void Given_EmptySize_When_ValidatingCommand_Then_SizeConstraintViolationReturned() {
    /*
        END OF SIZE TEST
     */
}