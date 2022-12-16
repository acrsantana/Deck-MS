package br.com.acrteck.planningpoker.decks.http;

import br.com.acrteck.planningpoker.decks.dto.CartaDto;
import br.com.acrteck.planningpoker.decks.model.Organizacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("cartas")
public interface CartasClient {
    @GetMapping(value = "/cartas/planningpoker/api/v1/cartas/{organizacao}")
    List<CartaDto> obtemCartasPorOrganizacao(@PathVariable Organizacao organizacao);
}
