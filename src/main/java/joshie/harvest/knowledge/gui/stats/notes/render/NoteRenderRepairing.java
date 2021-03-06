package joshie.harvest.knowledge.gui.stats.notes.render;

import joshie.harvest.api.core.ITiered.ToolTier;
import joshie.harvest.core.base.item.ItemTool;
import joshie.harvest.fishing.HFFishing;
import joshie.harvest.quests.player.trade.QuestBlacksmithing;
import joshie.harvest.tools.HFTools;
import net.minecraft.item.ItemStack;

import java.util.EnumMap;

public class NoteRenderRepairing extends NoteRenderHF {
    private EnumMap<ToolTier, ? extends ItemTool> map;
    private int ticker;

    private void updateToolForRender() {
        ticker++;
        if (map == null || ticker %120 == 0) {
            int number = rand.nextInt(6);
            if (number == 0) map = HFTools.HAMMERS;
            else if (number == 1) map = HFTools.WATERING_CANS;
            else if (number == 2) map = HFTools.AXES;
            else if (number == 3) map = HFTools.HOES;
            else if (number == 4) map = HFTools.SICKLES;
            else map = HFFishing.FISHING_RODS;
        }
    }

    @Override
    public void drawScreen(int x, int y) {
        updateToolForRender();
        drawTier(ToolTier.BASIC, -6, 40);
        drawTier(ToolTier.COPPER, -6, 60);
        drawTier(ToolTier.SILVER, -6, 80);
        drawTier(ToolTier.GOLD, -6, 100);
        drawTier(ToolTier.MYSTRIL, -6, 120);
        drawTier(ToolTier.MYTHIC, -6, 140);
    }

    private void drawTier(ToolTier tier, int left, int top) {
        long gold = QuestBlacksmithing.getCost(tier) / 10;
        ItemStack ore = QuestBlacksmithing.getRepairMaterial(tier);
        ItemStack tool = map.get(tier).getStack();
        int to = 70;
        drawArrow(left + to + 18, top + 3);
        drawStack(tool, left + to, top);
        drawStack(tool, left + to + 35, top);
        drawGold(gold, left + 26, top + 2);
        drawStack(ore, left + 5, top - 2);
    }
}
