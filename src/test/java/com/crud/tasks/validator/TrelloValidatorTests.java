package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloValidatorTests {

    TrelloValidator validator = new TrelloValidator();

    //improving coverage
    @Test
    public void shouldFetchValidatedBoards() {
        List<TrelloList> lists  = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1","name1",lists);
        TrelloBoard testBoard = new TrelloBoard("2","test",lists);
        TrelloBoard board3 = new TrelloBoard("3","name3",lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board1);
        boards.add(testBoard);
        boards.add(board3);
        List<TrelloBoard> validated = validator.validateTrelloBoards(boards);
        Assert.assertEquals(2,validated.size());
    }

    //improving coverage
    @Test
    public void shouldValidateCard() {
        TrelloCard card = new TrelloCard("Name","Desc","Top","1");
        TrelloCard test1 = new TrelloCard("Test1","Desc","Top","1");
        TrelloCard test2 = new TrelloCard("Test2","Desc","Top","1");
        Assert.assertEquals(true,validator.validateCard(card));
        Assert.assertEquals(false,validator.validateCard(test1));
        Assert.assertEquals(false,validator.validateCard(test2));
    }
}
