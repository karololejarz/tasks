package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        //18.4. metoda zwraca List<TrelloBoardDto>
        //return trelloClient.getTrelloBoards();

        //18.2-3 znoszone przez 18.4
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        List<TrelloBoardDto> selected = trelloBoards.stream().filter(b->!b.getId().isEmpty())
                .filter(b->b.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        selected.forEach(board -> {
            System.out.println(board.getId() + " " + board.getName() + '\n' +"This board contains lists: ");
            board.getLists().forEach(list -> System.out.println(list.getName()
                    + " " + list.getId() + " " + list.getIsClosed()));
        });

    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}