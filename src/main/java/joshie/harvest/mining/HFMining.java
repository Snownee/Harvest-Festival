package joshie.harvest.mining;

import joshie.harvest.api.HFApi;
import joshie.harvest.mining.blocks.*;
import joshie.harvest.mining.items.ItemMaterial;
import joshie.harvest.mining.loot.*;
import net.minecraft.world.DimensionType;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.DimensionManager;

import static joshie.harvest.core.config.General.MINING_ID;

public class HFMining {
    public static final DimensionType MINE_WORLD = DimensionType.register("The Mine", "_hf_mine", MINING_ID, MiningProvider.class, false);

    public static final BlockOre ORE = new BlockOre().register("ore");
    public static final BlockStone STONE = new BlockStone().register("stone");
    public static final BlockDirt DIRT = new BlockDirt().setBlockUnbreakable().register("dirt");
    public static final BlockLadder LADDER = new BlockLadder().register("ladder");
    public static final BlockPortal PORTAL = new BlockPortal().setBlockUnbreakable().register("portal");
    public static final ItemMaterial MATERIALS = new ItemMaterial().register("materials");

    public static void preInit() {
        DimensionManager.registerDimension(MINING_ID, MINE_WORLD);
        LootConditionManager.registerCondition(new Between.Serializer());
        LootConditionManager.registerCondition(new EndsIn.Serializer());
        LootConditionManager.registerCondition(new Exact.Serializer());
        LootConditionManager.registerCondition(new MultipleOf.Serializer());
        LootConditionManager.registerCondition(new Obtained.Serializer());
        HFApi.tickable.registerDailyTickableBlock(DIRT, new MiningTicker());
    }
}