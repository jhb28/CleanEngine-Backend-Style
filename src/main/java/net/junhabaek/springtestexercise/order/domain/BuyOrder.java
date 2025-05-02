package net.junhabaek.springtestexercise.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "buy_order")
@Table(name="buy_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BuyOrder {
    @Id @Column(name="buy_id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ticker", length = 10, nullable = false, updatable = false)
    private String ticker;

    @Column(name="user_id", nullable = false, updatable = false)
    private Long userId;

    // TODO size를 VO로 바꾸어야 함
    @Column(name="size", nullable = false)
    private Double size;

    // TODO price를 VO로 바꾸어야 함
    @Column(name="price", nullable = false)
    private Double price;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="is_marketorder", nullable = false, updatable = false)
    private Boolean isMarketOrder;

    public void buy(Double buySize){
        if(buySize > this.size) throw new IllegalArgumentException();
        this.size -= buySize;
    }

    public static BuyOrder create(String ticker, Long userId, Double size, Double price, Boolean isMarketOrder) {
        BuyOrder buyOrder = new BuyOrder();
        buyOrder.ticker = ticker;
        buyOrder.userId = userId;
        buyOrder.size = size;
        buyOrder.price = price;
        buyOrder.isMarketOrder = isMarketOrder;
        return buyOrder;
    }
}
