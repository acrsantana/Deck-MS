package br.com.acrteck.planningpoker.decks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class CartaDto implements Serializable {
    private Long id;
    @NotNull @Positive(message = "Esforço não pode ser negativo")
    private Float esforco;
    @NotNull
    private String valor;
    @NotNull
    private Integer idOrganizacao;
    private String organizacao;
}
