package joshie.harvest.quests.town.festivals.contest;

import joshie.harvest.api.npc.NPCEntity;
import joshie.harvest.api.quests.Selection;
import joshie.harvest.core.helpers.EntityHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ContestInfoMenu extends Selection<QuestContest> {
    public ContestInfoMenu(String prefix) {
        super("harvestfestival.quest.festival." + prefix + ".pick", "harvestfestival.quest.festival." + prefix + ".how",
                "harvestfestival.quest.festival." + prefix +".ready", "harvestfestival.quest.festival." + prefix + ".cancel");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result onSelected(EntityPlayer player, NPCEntity entity, QuestContest quest, int option) {
        if (option == 1) {
            quest.setStage(QuestContest.EXPLAIN);
            quest.syncData(player);
            return Result.ALLOW;
        } else if (option == 2) {
            quest.getEntries().getSelecting().add(EntityHelper.getPlayerUUID(player));
            quest.syncData(player);
            return Result.ALLOW;
        } else return Result.DENY;
    }
}
