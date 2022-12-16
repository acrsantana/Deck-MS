package br.com.acrteck.planningpoker.decks.exception;

public class FalhaAoRecuperarDecksException extends RuntimeException {
    public FalhaAoRecuperarDecksException(String message) {
        super(message);
    }

    public FalhaAoRecuperarDecksException(String message, Throwable cause) {
        super(message, cause);
    }
}
