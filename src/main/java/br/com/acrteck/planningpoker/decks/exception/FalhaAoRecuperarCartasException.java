package br.com.acrteck.planningpoker.decks.exception;

public class FalhaAoRecuperarCartasException extends RuntimeException {
    public FalhaAoRecuperarCartasException(String message) {
        super(message);
    }

    public FalhaAoRecuperarCartasException(String message, Throwable cause) {
        super(message, cause);
    }
}
