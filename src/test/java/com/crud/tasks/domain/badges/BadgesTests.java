package com.crud.tasks.domain.badges;

import org.junit.Assert;
import org.junit.Test;

public class BadgesTests {
    Trello trello = new Trello(1,2);
    AttachmentsByType att = new AttachmentsByType(trello);
    TrelloBadges badge = new TrelloBadges(1,att);
    //Improving coverage
    @Test
    public void getBadge() {
        Assert.assertEquals(1,badge.getVotes());
        Assert.assertEquals(att,badge.getAttachmentsByType());
        Assert.assertEquals(trello,badge.getAttachmentsByType().getTrello());
        Assert.assertEquals(1,badge.getAttachmentsByType().getTrello().getBoard());
        Assert.assertEquals(2,badge.getAttachmentsByType().getTrello().getCard());
    }

}
