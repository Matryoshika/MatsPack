package matryoshika.unknowntweaks.crafttweaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SecretRecipes {
	
	public static List<MutableTriple<String, ItemStack, Integer>> CACHE = new ArrayList<MutableTriple<String, ItemStack, Integer>>();
	
	/**
	 * Scans Block & Item Registries ONCE, and populates CACHE so that no further massive lookups are needed.
	 */
	public static void populateCache() {
		CACHE.add(new MutableTriple("", ItemStack.EMPTY, 0));
		
		List<Triple<String, ItemStack, Integer>> COPY = new ArrayList<Triple<String, ItemStack, Integer>>(CACHE);
		
		ForgeRegistries.BLOCKS.getValues().forEach(block -> {
			COPY.forEach(triple -> {
				if(triple.getLeft().equals(Salter.getSalted(block.getRegistryName().toString())))
					CACHE.get(CACHE.indexOf(triple)).middle = new ItemStack(block);
			});
		});
		
		ForgeRegistries.ITEMS.getValues().forEach(item -> {
			COPY.forEach(triple -> {
				if(triple.getLeft().equals(Salter.getSalted(item.getRegistryName().toString())))
					CACHE.get(CACHE.indexOf(triple)).middle = new ItemStack(item);
			});
		});
	}
	
	public static void loadSecretRecipes() {
		if(CACHE.isEmpty())
			populateCache();
	}
	
	
	public static ItemStack get(String s) {
		ItemStack stack = ItemStack.EMPTY;
		
		for(MutableTriple<String, ItemStack, Integer> triple : CACHE) 
			if(triple.getLeft().equals(s)) {
				stack = triple.getMiddle();
				stack.setItemDamage(triple.right);
			}
		return stack;
	}

}
