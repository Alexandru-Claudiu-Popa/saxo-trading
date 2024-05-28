package dev.saxo.trading.errors;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String saxoSecretKey) {
        super("Account with secret key '" + saxoSecretKey + "' not found");
    }
}
