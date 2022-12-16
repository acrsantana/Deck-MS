package br.com.acrteck.planningpoker.decks.repository;

import br.com.acrteck.planningpoker.decks.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    Optional<Deck> findByNome(String nome);
}
