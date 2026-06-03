package joshie.harvest.quests.base;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.Quest;
import net.minecraft.entity.player.EntityPlayer;

public abstract class QuestFriendshipStore extends QuestFriendship {
	public QuestFriendshipStore(NPC npc, int relationship) {
		super(npc, relationship);
	}

	protected abstract Quest getQuest();

	@Override
	public void onQuestCompleted(EntityPlayer player) {
		HFApi.quests.completeQuestConditionally(getQuest(), player);
		super.onQuestCompleted(player);
	}
}
