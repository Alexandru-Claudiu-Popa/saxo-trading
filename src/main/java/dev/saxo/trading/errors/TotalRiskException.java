package dev.saxo.trading.errors;

public class TotalRiskException extends RuntimeException {

    public TotalRiskException(String saxoSecretKey) {
        super("Total risk exceeded for account with secret key '" + saxoSecretKey + "'");
    }
}
