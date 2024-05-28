package dev.saxo.trading.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTS")
public class AccountEntity {

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SAXO_API_KEY", nullable = false, length = 50)
    private String saxoApiKey;

    @Id
    @Column(name = "SAXO_SECRET_KEY", nullable = false, length = 25)
    private String saxoSecretKey;

    @Column(name = "DAILY_RISK", nullable = false, precision = 5, scale = 2)
    private BigDecimal dailyRisk;

    @Column(name = "MAX_RISK", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxRisk;

    @Column(name = "CREATED_TIME", nullable = false)
    private Instant createdTime;

    @Column(name = "MODIFIED_TIME")
    private Instant modifiedTime;
}