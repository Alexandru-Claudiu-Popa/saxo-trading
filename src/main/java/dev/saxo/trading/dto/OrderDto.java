package dev.saxo.trading.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {

    private String secret;
    private String symbol;
    @JsonProperty("BuySell")
    private String buySell;
    @JsonProperty("OrderType")
    private String orderType;
    @JsonProperty("PositionSize")
    private Integer positionSize;
    @JsonProperty("Strategy")
    private String strategy;
    private BigDecimal slPercentPerTrade;
}
