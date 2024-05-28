package dev.saxo.trading.util;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {
    private String symbol;
    private BigDecimal buy;
    private BigDecimal sell;
}
