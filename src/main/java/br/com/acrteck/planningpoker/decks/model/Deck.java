package br.com.acrteck.planningpoker.decks.model;

import br.com.acrteck.planningpoker.decks.dto.DeckDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity @Table(name = "decks")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Deck {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Lob @Column(nullable = false)
    private byte[] arquivo;

    public Deck(DeckDto deckDto) {
        this.nome = deckDto.getNome();
        this.arquivo = deckDto.getArquivo();
    }
}
