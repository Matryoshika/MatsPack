package matryoshika.unknowntweaks.content;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod.EventBusSubscriber
public class ContentRegistry {
	
	@ObjectHolder("unknowntweaks:basic_pickupper")
	public static final Block basic_pickupper = null;
	
	
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		event.getRegistry().registerAll(
				new BlockBasicPickUpper()
				
		);
	}
	
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		event.getRegistry().registerAll(
				getItemBlock(basic_pickupper)
		);
	}
	
	
	public static Item getItemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

}
