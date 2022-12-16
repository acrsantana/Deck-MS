package br.com.acrteck.planningpoker.decks.exception;

public class ArquivoNaoEncontradoException extends RuntimeException {
    public ArquivoNaoEncontradoException(String message) {
        super(message);
    }

    public ArquivoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
