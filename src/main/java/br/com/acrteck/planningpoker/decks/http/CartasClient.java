package br.com.acrteck.planningpoker.decks.http;

import br.com.acrteck.planningpoker.decks.dto.CartaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("http://localhost:8080/cartas/planningpoker/api/v1/cartas")
public interface CartasClient {
    @GetMapping(value = "/organizacao/{idOrganizacao}")
    List<CartaDto> obtemCartasPorOrganizacao(@PathVariable Integer idOrganizacao);
}
