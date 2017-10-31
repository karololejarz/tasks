package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    //changed from void to boolean - LOGGER tough to track
    public boolean validateCard(final TrelloCard trelloCard) {
        if(trelloCard.getName().contains("test")||trelloCard.getName().contains("Test")) {
            LOGGER.info("Somebody is testing my application!");
            return false;
        } else {
            LOGGER.info("Seems that my application is used in proper way.");
            return true;
        }
    }

    public List<TrelloBoard> validateTrelloBoards (final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                //was not working with equalsIgnoreCase - in test showed cases("test")
                .filter(trelloBoard->!trelloBoard.getName().equals("test"))
                .collect(toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());
        return filteredBoards;
    }
}

