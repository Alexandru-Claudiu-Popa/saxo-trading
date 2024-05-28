package dev.saxo.trading.errors;

public class SymbolNotFoundException extends RuntimeException {

    public SymbolNotFoundException(String symbol) {
        super("Symbol '" + symbol + "' not found");
    }
}
