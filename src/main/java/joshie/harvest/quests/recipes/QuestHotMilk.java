package joshie.harvest.quests.recipes;

import joshie.harvest.api.HFQuest;
import joshie.harvest.npc.HFNPCs;

@HFQuest(data = "recipe.milk.hot")
public class QuestHotMilk extends QuestRecipe {
    public QuestHotMilk() {
        super("milk_hot", HFNPCs.MILKMAID, 5000);
    }
}
