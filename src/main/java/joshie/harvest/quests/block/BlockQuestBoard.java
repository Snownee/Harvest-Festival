package joshie.harvest.quests.block;

import java.util.Locale;

import javax.annotation.Nonnull;

import joshie.harvest.HarvestFestival;
import joshie.harvest.api.quests.Quest;
import joshie.harvest.core.HFTab;
import joshie.harvest.core.base.block.BlockHFEnumRotatableMeta;
import joshie.harvest.core.handlers.GuiHandler;
import joshie.harvest.quests.block.BlockQuestBoard.QuestBlock;
import joshie.harvest.town.TownHelper;
import joshie.harvest.town.data.TownData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockQuestBoard extends BlockHFEnumRotatableMeta<BlockQuestBoard, QuestBlock> {
    private static final AxisAlignedBB WEST = new AxisAlignedBB(0.8D, 0D, 0D, 1.0D, 1D, 1D);
    private static final AxisAlignedBB SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.2D);
    private static final AxisAlignedBB EAST = new AxisAlignedBB(0D, 0D, 0D, 0.2D, 1D, 1D);
    private static final AxisAlignedBB NORTH = new AxisAlignedBB(0D, 0D, 0.8D, 1D, 1D, 1D);

    public BlockQuestBoard() {
        super(Material.WOOD, QuestBlock.class, HFTab.TOWN);
        setHardness(1.5F);
    }

    public enum QuestBlock implements IStringSerializable {
        BOARD, ACTIVE;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        EnumFacing facing = state.getValue(FACING);
        switch (facing) {
        case NORTH:
            return BlockQuestBoard.NORTH;
        case EAST:
            return BlockQuestBoard.EAST;
        case WEST:
            return BlockQuestBoard.WEST;
        case SOUTH:
            return BlockQuestBoard.SOUTH;
        default:
            return FULL_BLOCK_AABB;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public IBlockState withRotation(@Nonnull IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)).getOpposite());
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    @SuppressWarnings("ConstantConditions, deprecation")
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile == null) {
            return state;
        }
        TownData data = TownHelper.getClosestTownToBlockPos(tile.getWorld(), pos, false);
        Quest quest = data.getDailyQuest();
        if (quest != null && !data.getQuests().getCurrent().contains(quest)) {
            return state.withProperty(property, QuestBlock.ACTIVE);
        }

        return state;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        player.openGui(HarvestFestival.instance, GuiHandler.QUEST_BOARD, world, pos.getX(), pos.getY(), pos.getZ()); //Open the GUI
        return true;
    }

    @Override
    protected boolean shouldDisplayInCreative(QuestBlock block) {
        return block == QuestBlock.BOARD;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    @Nonnull
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileQuestBoard();
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
