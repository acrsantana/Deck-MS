package br.com.acrteck.planningpoker.decks.exception;

public class FalhaAoGravarDecksException extends RuntimeException {
    public FalhaAoGravarDecksException(String message) {
        super(message);
    }

    public FalhaAoGravarDecksException(String message, Throwable cause) {
        super(message, cause);
    }
}
