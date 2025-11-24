package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {
        // Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Test List", false);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Test Board", Arrays.asList(trelloListDto));
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto);

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        // Then
        assertNotNull(trelloBoards);
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Test Board", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
        assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
        assertEquals("Test List", trelloBoards.get(0).getLists().get(0).getName());
        assertEquals(false, trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    void testMapToBoardsDto() {
        // Given
        TrelloList trelloList = new TrelloList("1", "Test List", false);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Test Board", Arrays.asList(trelloList));
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        // Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("Test Board", trelloBoardDtoList.get(0).getName());
        assertEquals(1, trelloBoardDtoList.get(0).getLists().size());
        assertEquals("1", trelloBoardDtoList.get(0).getLists().get(0).getId());
        assertEquals("Test List", trelloBoardDtoList.get(0).getLists().get(0).getName());
        assertEquals(false, trelloBoardDtoList.get(0).getLists().get(0).isClosed());
    }

    @Test
    void testMapToList() {
        // Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Test List", false);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto);

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        // Then
        assertNotNull(trelloLists);
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Test List", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    void testMapToListDto() {
        // Given
        TrelloList trelloList = new TrelloList("1", "Test List", false);
        List<TrelloList> trelloListList = Arrays.asList(trelloList);

        // When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        // Then
        assertNotNull(trelloListDtoList);
        assertEquals(1, trelloListDtoList.size());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertEquals("Test List", trelloListDtoList.get(0).getName());
        assertEquals(false, trelloListDtoList.get(0).isClosed());
    }

    @Test
    void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Test Description", "top", "1");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertNotNull(trelloCard);
        assertEquals("Test Card", trelloCard.getName());
        assertEquals("Test Description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Test Card", "Test Description", "top", "1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertNotNull(trelloCardDto);
        assertEquals("Test Card", trelloCardDto.getName());
        assertEquals("Test Description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }
}
