package joshie.harvest.npcs.greeting;

import joshie.harvest.api.calendar.Festival;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.npc.greeting.IConditionalGreeting;
import joshie.harvest.core.helpers.TextHelper;
import joshie.harvest.core.util.Util;
import joshie.harvest.town.TownHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;

public class GreetingFestival implements IConditionalGreeting {
    private final String text;
    private final Festival festival;

    public GreetingFestival(Festival festival) {
        this.festival = festival;
        this.text = "harvestfestival.npc.%s.festival." + festival.getResource().getResourcePath().replace("_", ".");
    }

    @Override
    public boolean canDisplay(EntityPlayer player, EntityAgeable ageable, NPC npc) {
        return TownHelper.getClosestTownToEntity(player, false).getFestival() == festival;
    }

    @Override
    public double getDisplayChance() {
        return 100D;
    }

    @Override
    public String getLocalizedText(EntityPlayer player, EntityAgeable ageable, NPC npc) {
        return TextHelper.getRandomSpeech(npc, Util.safeFormat(text, npc.getResource().getResourcePath()), 10);
    }
}
