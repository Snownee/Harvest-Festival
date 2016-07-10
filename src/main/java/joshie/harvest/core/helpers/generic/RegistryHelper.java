package joshie.harvest.core.helpers.generic;

import joshie.harvest.animals.render.FakeAnimalRenderer;
import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.npc.NPC;
import joshie.harvest.npc.render.FakeNPCRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static joshie.harvest.core.lib.HFModInfo.MODID;
import static joshie.harvest.npc.HFNPCs.SPAWNER_NPC;

public class RegistryHelper {
    public static void registerTiles(Class<? extends TileEntity>... tiles) {
        for (Class<? extends TileEntity> tile : tiles) {
            GameRegistry.registerTileEntity(tile, MODID + ":" + tile.getSimpleName().replace("Tile", "").toLowerCase());
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRendererItem(ItemStack stack, ModelBase model) {
        String name = model.getClass().getSimpleName().replace("ModelHarvest", "").toLowerCase();
        Class fake = FakeTileHelper.getFakeClass("Fake" + name, HFModInfo.FAKEANIMAL);
        if (fake != null) {
            ForgeHooksClient.registerTESRItemStack(stack.getItem(), stack.getItemDamage(), fake);
            ClientRegistry.bindTileEntitySpecialRenderer(fake, new FakeAnimalRenderer(name, model));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerNPCRendererItem(NPC npc) {
        Class fake = FakeTileHelper.getFakeClass(npc.getRegistryName().toString().replace(":", ""), HFModInfo.FAKENPC);
        if (fake != null) {
            ItemStack stack = SPAWNER_NPC.getStackFromObject(npc);
            ForgeHooksClient.registerTESRItemStack(stack.getItem(), stack.getItemDamage(), fake);
            ClientRegistry.bindTileEntitySpecialRenderer(fake, new FakeNPCRenderer(npc));
        }
    }
}