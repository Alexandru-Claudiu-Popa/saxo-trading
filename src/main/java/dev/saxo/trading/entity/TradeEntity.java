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
@Table(name = "TRADES")
public class TradeEntity {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "SAXO_SECRET_KEY", nullable = false)
    private AccountEntity account;

    @Column(name = "STATUS", nullable = false, length = 6)
    private String status;

    @Column(name = "BUY_SELL_PRICE", nullable = false, precision = 12, scale = 2)
    private BigDecimal buySellPrice;

    @Column(name = "CLOSE_PRICE", precision = 12, scale = 2)
    private BigDecimal closePrice;

    @Column(name = "OPEN_DATE_TIME", nullable = false)
    private Instant openDateTime;

    @Column(name = "CLOSE_DATE_TIME")
    private Instant closeDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private OrderEntity order;
}