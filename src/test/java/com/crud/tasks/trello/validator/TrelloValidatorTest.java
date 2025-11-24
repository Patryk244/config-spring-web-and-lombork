package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    void testValidateTrelloBoards() {
        // Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "board", new ArrayList<>()));

        // When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        // Then
        assertNotNull(validatedBoards);
        assertEquals(1, validatedBoards.size());
        assertEquals("board", validatedBoards.get(0).getName());
    }

    @Test
    void testValidateCard() {
        // Given
        TrelloCard trelloCardWithTest = new TrelloCard("test card", "description", "pos", "1");
        TrelloCard trelloCardWithoutTest = new TrelloCard("card", "description", "pos", "1");

        // When & Then
        trelloValidator.validateCard(trelloCardWithTest);
        trelloValidator.validateCard(trelloCardWithoutTest);
    }
}
