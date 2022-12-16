package br.com.acrteck.planningpoker.decks.exception;

public class DeckNaoEncontradoException extends RuntimeException {
    public DeckNaoEncontradoException(String message) {
        super(message);
    }

    public DeckNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
