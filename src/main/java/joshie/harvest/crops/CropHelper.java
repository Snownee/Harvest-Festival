package joshie.harvest.crops;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.calendar.Weather;
import joshie.harvest.api.crops.IStateHandler.PlantSection;
import joshie.harvest.api.crops.WateringHandler;
import joshie.harvest.core.entity.EntityBasket;
import joshie.harvest.core.helpers.SpawnItemHelper;
import joshie.harvest.crops.block.BlockHFCrops;
import joshie.harvest.crops.tile.TileWithered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

import static joshie.harvest.api.crops.IStateHandler.PlantSection.BOTTOM;

public class CropHelper {
    private static final Cache<BlockPos, IBlockState> RESERVE = CacheBuilder.newBuilder().expireAfterAccess(100, TimeUnit.MILLISECONDS).maximumSize(64).build();

    public static void onBottomBroken(BlockPos pos, IBlockState state) {
        RESERVE.put(pos, state);
    }

    public static IBlockState getTempState(BlockPos pos) {
        return RESERVE.getIfPresent(pos);
    }

    public static TileWithered getTile(IBlockAccess world, BlockPos pos, PlantSection section) {
        if (section == BOTTOM) return (TileWithered) (world instanceof ChunkCache ? ((ChunkCache)world).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos));
        else {
            TileWithered down = ((TileWithered)(world instanceof ChunkCache ? ((ChunkCache)world).getTileEntity(pos.down(), Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos.down())));
            return down == null ? (TileWithered) (world instanceof ChunkCache ? ((ChunkCache)world).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos)): down;
        }
    }

    //Returns whether the farmland is hydrated
    public static boolean isWetSoil(World world, BlockPos pos, IBlockState state) {
        WateringHandler handler = getWateringHandler(world, pos, state);
        return handler != null && handler.isWet(world, pos, state);
    }

    //Returns true if this is waterable
    @Nullable
    public static WateringHandler getWateringHandler(World world, BlockPos pos, IBlockState state) {
        for (WateringHandler checker: CropRegistry.INSTANCE.wateringHandlers) {
            if (checker.handlesState(world, pos, state)) return checker;
        }

        return null;
    }

    //Harvests the crop at this location
    public static boolean harvestCrop(EntityPlayer player, World world, BlockPos pos) {
        NonNullList<ItemStack> list = HFApi.crops.harvestCrop(player, world, pos);
        if (!world.isRemote && !list.isEmpty()) {
            EntityBasket.findBasketAndShip(player, list);
            //Spawn them items
            for (ItemStack stack: list) {
                SpawnItemHelper.dropBlockAsItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }

        return !list.isEmpty();
    }

    @Nullable
    public static CropData getCropDataAt(IBlockAccess world, BlockPos pos) {
        PlantSection section = BlockHFCrops.getSection(world.getBlockState(pos));
        if (section == PlantSection.BOTTOM) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileWithered) return ((TileWithered)tile).getData();
        } else if (section == PlantSection.TOP) {
            TileEntity tile = world.getTileEntity(pos.down());
            if (tile instanceof TileWithered) return ((TileWithered)tile).getData();
        }

        return null;
    }

    @Nullable
    static Season getSeasonAt(IBlockAccess world, BlockPos pos) {
        PlantSection section = BlockHFCrops.getSection(world.getBlockState(pos));
        if (section == PlantSection.BOTTOM) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null) {
                return HFApi.calendar.getSeasonAtCoordinates(tile.getWorld(), pos);
            }
        } else if (section == PlantSection.TOP) {
            TileEntity tile = world.getTileEntity(pos.down());
            if (tile != null) {
                return HFApi.calendar.getSeasonAtCoordinates(tile.getWorld(), pos.down());
            }
        }

        return null;
    }

    public static boolean isRainingAt(World world, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        Weather weather = HFApi.calendar.getWeather(world);
        return (weather.isRain() || (weather.isSnow() && biome.isHighHumidity())) && world.canBlockSeeSky(pos) && world.getPrecipitationHeight(pos).getY() <= pos.getY() && world.getBiome(pos).canRain();
    }
}