package br.com.acrteck.planningpoker.decks.exception;

public class FalhaAoRecuperarArquivoException extends RuntimeException {
    public FalhaAoRecuperarArquivoException(String message) {
        super(message);
    }

    public FalhaAoRecuperarArquivoException(String message, Throwable cause) {
        super(message, cause);
    }
}
