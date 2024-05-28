package dev.saxo.trading.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("apiKey")
    private String saxoApiKey;

    @JsonProperty("secretKey")
    private String saxoSecretKey;

    @JsonProperty("dailyRisk")
    private BigDecimal dailyRisk;

    @JsonProperty("maxRisk")
    private BigDecimal maxRisk;
}
