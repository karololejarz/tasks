package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTests {

    TrelloMapper mapper = new TrelloMapper();

    public List<TrelloList> createTrelloLists(){
        List<TrelloList> trelloListsList = new ArrayList();
        TrelloList list1 = new TrelloList("id1","title1",true);
        TrelloList list2 = new TrelloList("id2","title2",false);
        trelloListsList.add(list1);
        trelloListsList.add(list2);
        return trelloListsList;
    }
    public List<TrelloListDto> createTrelloListDtosList(){
        List<TrelloListDto> trelloListDtosList = new ArrayList();
        TrelloListDto list1 = new TrelloListDto("id1","title1",true);
        TrelloListDto list2 = new TrelloListDto("id2","title2",false);
        trelloListDtosList.add(list1);
        trelloListDtosList.add(list2);
        return trelloListDtosList;
    }

    @Test
    public void shouldMapToTrelloBoards() {
        List<TrelloListDto> list = createTrelloListDtosList();
        TrelloBoardDto boardDto1 = new TrelloBoardDto("id1","name1",list);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("id2","name2",list);
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(boardDto1);
        boardDtos.add(boardDto2);
        List<TrelloBoard> expected = mapper.mapToBoards(boardDtos);
        Assert.assertEquals(2,boardDto1.getLists().size());
        Assert.assertEquals(2,expected.size());
        Assert.assertEquals("id2",expected.get(1).getId());
    }

    @Test
    public void shouldMapToTrelloBoardDtos() {
        List<TrelloList> list = createTrelloLists();
        TrelloBoard board1 = new TrelloBoard("id1","name1",list);
        TrelloBoard board2 = new TrelloBoard("id2","name2",list);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board1);
        boards.add(board2);
        List<TrelloBoardDto> expected = mapper.mapToBoardsDto(boards);
        Assert.assertEquals("id1",expected.get(0).getId());
        Assert.assertEquals(2,board1.getLists().size());
        Assert.assertEquals(2,expected.size());
    }

    @Test
    public void shouldMapToCard(){
        TrelloCardDto cardDto = new TrelloCardDto("name","description","pos","listId");
        TrelloCard expected = mapper.mapToCard(cardDto);
        Assert.assertEquals("name",expected.getName());
        Assert.assertEquals("description",expected.getDescription());
        Assert.assertEquals("pos",expected.getPos());
        Assert.assertEquals("listId",expected.getListId());
    }

    @Test
    public void shouldMapToCardDto(){
        TrelloCard card = new TrelloCard("name","description","pos","listId");
        TrelloCardDto expected = mapper.mapToCardDto(card);
        Assert.assertEquals("name",expected.getName());
        Assert.assertEquals("description",expected.getDescription());
        Assert.assertEquals("pos",expected.getPos());
        Assert.assertEquals("listId",expected.getListId());
    }
}
