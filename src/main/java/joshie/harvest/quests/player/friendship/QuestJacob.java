package joshie.harvest.quests.player.friendship;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npc.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestFriendship;

import java.util.Set;

@HFQuest("friendship.jacob.hatchery")
public class QuestJacob extends QuestFriendship {
    public QuestJacob() {
        super(HFNPCs.FISHERMAN, 15000);
    }

    @Override
    public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
        return finished.contains(Quests.RECIPE_GRILLED_FISH);
    }
}
