package dev.saxo.trading.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "SAXO_SECRET_KEY", nullable = false)
    private AccountEntity account;

    @Column(name = "SYMBOL", nullable = false, length = 15)
    private String symbol;

    @Column(name = "BUY_SELL", nullable = false, length = 4)
    private String buySell;

    @Column(name = "ORDER_TYPE", nullable = false, length = 10)
    private String orderType;

    @Column(name = "POSITION_SIZE", nullable = false)
    private Integer positionSize;

    @Column(name = "STRATEGY", nullable = false, length = 20)
    private String strategy;

    @Column(name = "SL_PERCENT_PER_TRADE", nullable = false, precision = 4, scale = 2)
    private BigDecimal slPercentPerTrade;

    @Column(name = "STATUS", nullable = false, length = 8)
    private String status;

    @Column(name = "CREATED_TIME", nullable = false)
    private Instant createdTime;
}