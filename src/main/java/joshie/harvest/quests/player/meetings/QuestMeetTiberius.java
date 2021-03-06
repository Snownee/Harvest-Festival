package joshie.harvest.quests.player.meetings;

import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.knowledge.HFKnowledge;
import joshie.harvest.knowledge.item.ItemBook.Book;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.base.QuestMeeting;
import joshie.harvest.town.TownHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

@HFQuest("meeting.tiberius")
public class QuestMeetTiberius extends QuestMeeting {
    public QuestMeetTiberius() {
        super(HFBuildings.CLOCKMAKER, HFNPCs.CLOCKMAKER);
    }

    @Override
    public String getDescription(World world, EntityPlayer player) {
        if (hasBuilding(player)) return getLocalized("description");
        else if (TownHelper.getClosestTownToEntity(player, false).hasBuildings(building.getRequirements())) return getLocalized("build");
        else return null;
    }

    @Override
    @Nonnull
    public ItemStack getCurrentIcon(World world, EntityPlayer player) {
        return hasBuilding(player) ? primary : buildingStack;
    }

    @Override
    public void onQuestCompleted(EntityPlayer player) {
        rewardItem(player, new ItemStack(Items.CLOCK));
        rewardItem(player, HFKnowledge.BOOK.getStackFromEnum(Book.CALENDAR));
    }
}
