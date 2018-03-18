package matryoshika.unknowntweaks.content;

import matryoshika.unknowntweaks.UTUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileSuperPickUpper extends TileEntity{

	@SubscribeEvent
	public void pickUp(EntityJoinWorldEvent event) {
		if (!(event.getEntity() instanceof EntityItem))
			return;

		EntityItem entity = (EntityItem) event.getEntity();
		Chunk self = getWorld().getChunkFromBlockCoords(getPos());
		if (self.x != getWorld().getChunkFromBlockCoords(entity.getPosition()).x && self.z != getWorld().getChunkFromBlockCoords(entity.getPosition()).z)
			return;

		BlockPos corner1 = new BlockPos(getPos().getX() - 7, getPos().getY() - 7, getPos().getZ() - 7);
		BlockPos corner2 = new BlockPos(getPos().getX() + 7, getPos().getY() + 7, getPos().getZ() + 7);
		if (!UTUtils.isInside(entity.getPosition(), BlockPos.getAllInBox(corner1, corner2)))
			return;

		EnumFacing facing = this.world.getBlockState(this.pos).getValue(BlockBasicPickUpper.FACING);
		TileEntity te = this.world.getTileEntity(this.pos.offset(facing.getOpposite()));

		if (te == null || !te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite()))
			return;

		IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
		ItemStack left = ItemHandlerHelper.insertItemStacked(itemHandler, entity.getItem().copy(), false);

		if (left.isEmpty() || left.getCount() == 0)
			entity.setDead();
		else
			entity.setItem(left);

	}

}
