package dev.saxo.trading.errors.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaxoResponse {
    private Integer code;
    private String message;
}
