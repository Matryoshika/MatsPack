package matryoshika.unknowntweaks.content;

import java.util.List;

import matryoshika.unknowntweaks.UTUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileBasicPickUpper extends TileEntity implements ITickable {

	public int TICKS = 0;

	@Override
	public void update() {
		if (!this.world.isRemote) {
			TICKS++;
			
			if (TICKS >= 100) {
				TICKS = 0;

				List<EntityItem> ents = this.world.getEntitiesWithinAABB(EntityItem.class, UTUtils.getBB(getPos(), 2), EntitySelectors.IS_ALIVE);

				if (!ents.isEmpty()) {
					EnumFacing facing = this.world.getBlockState(this.pos).getValue(BlockBasicPickUpper.FACING);
					TileEntity te = this.world.getTileEntity(this.pos.offset(facing.getOpposite()));

					if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite())) {
						final IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
						ents.forEach(entityitem -> {
							ItemStack original = entityitem.getItem().copy();
							ItemStack left = ItemHandlerHelper.insertItemStacked(itemHandler, original, false);

							if (left.isEmpty() || left.getCount() == 0) {
								entityitem.setDead();
							} else {
								entityitem.setItem(left);
							}
						});
					}
				}
			}
		}
	}

}
