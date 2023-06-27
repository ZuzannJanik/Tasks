package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "New List", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("3", "New Board", trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> resultList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(trelloBoardDtoList.get(0).getId(), resultList.get(0).getId());
    }

    @Test
    void mapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "New List", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);

        TrelloBoard trelloBoard1 = new TrelloBoard("3", "New Board", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);

        //When
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoards.get(0).getId(), resultList.get(0).getId());
    }

    @Test
    void mapToList() {
        //Given
        TrelloListDto trelloList = new TrelloListDto("1", "New List", true);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloList> resultList = trelloMapper.mapToList(trelloLists);

        //Then
        assertEquals(trelloLists.get(0).getId(), resultList.get(0).getId());
    }

    @Test
    void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "New List", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloLists.get(0).getId(), resultList.get(0).getId());
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card Name", "Card Description", "Card pos", "1");

        //When
        TrelloCardDto resultCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getListId(), resultCard.getListId());
    }

    @Test
    void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card Name", "Card Description", "Card pos", "1");

        //When
        TrelloCard resultCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getListId(), resultCard.getListId());
    }
}