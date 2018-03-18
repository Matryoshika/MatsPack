package matryoshika.unknowntweaks.content;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockMediocrePickUpper extends BlockDirectional {

	public BlockMediocrePickUpper() {
		super(Material.IRON);
		setRegistryName(new ResourceLocation(UnknownTweaks.MODID, "mediocre_pickupper"));
		setUnlocalizedName(getRegistryName().toString());
		setHardness(3.0F);
		setResistance(5.0F);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileMediocrePickUpper();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).ordinal();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return canPlaceBlock(worldIn, pos, facing.getOpposite()) ? getDefaultState().withProperty(FACING, facing) : getDefaultState().withProperty(FACING, EnumFacing.DOWN);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos changedPos) {
		if (checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, state.getValue(FACING).getOpposite())) {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (canPlaceBlockAt(worldIn, pos))
			return true;
		else {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return canPlaceBlock(worldIn, pos, side.getOpposite());
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.values())
			if (canPlaceBlock(worldIn, pos, enumfacing))
				return true;
		return false;
	}

	protected static boolean canPlaceBlock(World world, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.DOWN && isBlockInventory(world, pos.down(), face) ? true : isBlockInventory(world, pos.offset(face), face);
	}

	private static boolean isBlockInventory(World world, BlockPos pos, EnumFacing facing) {
		TileEntity te = world.getTileEntity(pos);
		if (te == null)
			return false;

		return te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
