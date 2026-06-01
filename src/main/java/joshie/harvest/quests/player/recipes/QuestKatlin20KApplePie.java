package joshie.harvest.quests.player.recipes;

import java.util.Set;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.Quests;
import joshie.harvest.quests.base.QuestRecipe;

@HFQuest("recipe.pie_apple")
public class QuestKatlin20KApplePie extends QuestRecipe {
	public QuestKatlin20KApplePie() {
		super("pie_apple", HFNPCs.CAFE_GRANNY, 20000);
	}

	@Override
	public boolean canStartQuest(Set<Quest> active, Set<Quest> finished) {
		return finished.contains(Quests.KATLIN_15K);
	}
}
