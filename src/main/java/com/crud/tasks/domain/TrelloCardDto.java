package com.crud.tasks.domain;

import com.crud.tasks.domain.badges.AttachmentsByType;
import com.crud.tasks.domain.badges.TrelloBadges;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
    private TrelloBadges badge;
}
