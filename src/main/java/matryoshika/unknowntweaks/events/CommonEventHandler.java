package matryoshika.unknowntweaks.events;

import java.util.ArrayList;
import java.util.List;

import matryoshika.unknowntweaks.worldgen.BlockRemover;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.items.CapabilityItemHandler;

@Mod.EventBusSubscriber()
public class CommonEventHandler {

	private static int ticks = 0;
	public static List<Entity> entitiesToKill = new ArrayList<Entity>();

	@SubscribeEvent
	public static void replace(EntityJoinWorldEvent event) {
	}
	
	@SubscribeEvent
	public static void worldTick(WorldTickEvent event) {
	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		event.getWorld().addEventListener(new BlockRemover());
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(EntityInteractSpecific event) {
		if(event.getEntityPlayer().world.isRemote)
			return;
		
		if(event.getTarget() instanceof EntitySheep && !((EntitySheep)event.getTarget()).getSheared()) {
			if(event.getEntityPlayer().getHeldItem(event.getHand()).getItem() == Items.FLINT) {
				event.getEntityPlayer().getHeldItem(event.getHand()).shrink(1);
				ItemStack stack = new ItemStack(Items.STRING);
				
				for(int i = 0; i < 36; i++) {
					stack = event.getEntityPlayer().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).insertItem(i, stack, false);
					if(stack.isEmpty()) {
						break;
					}
					if(i == 35 && !stack.isEmpty()) {
						EntityItem ent = new EntityItem(event.getEntityPlayer().world);
						ent.setItem(stack);
						event.getTarget().entityDropItem(stack, 1.0F);
	                    ent.motionY += event.getEntityPlayer().world.rand.nextFloat() * 0.05F;
	                    ent.motionX += (event.getEntityPlayer().world.rand.nextFloat() - event.getEntityPlayer().world.rand.nextFloat()) * 0.1F;
	                    ent.motionZ += (event.getEntityPlayer().world.rand.nextFloat() - event.getEntityPlayer().world.rand.nextFloat()) * 0.1F;
					}
				}
				((EntitySheep)event.getTarget()).setSheared(true);
				((EntitySheep)event.getTarget()).playSound(SoundEvents.BLOCK_CLOTH_BREAK, 1.0F, 1.0F);
			}
		}
	}

}
