package dev.saxo.trading.errors;

public class DailyRiskException extends RuntimeException {

    public DailyRiskException(String saxoSecretKey) {
        super("Daily risk exceeded for account with secret key '" + saxoSecretKey + "'");
    }
}
