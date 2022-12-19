package br.com.acrteck.planningpoker.decks.dto;

import br.com.acrteck.planningpoker.decks.model.Deck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DeckDto {

    private Long id;
    @NotBlank @Max(value = 50, message = "O nome do deck não pode exceder 50 caracteres")
    private String nome;
    private byte[] arquivo;
    private List<CartaDto> cartas;
    private Integer idOrganizacao;

    public DeckDto(Deck deck) {
        BeanUtils.copyProperties(deck, this);
    }
}
