package matryoshika.unknowntweaks.worldgen;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod.EventBusSubscriber
public class WorldGenEvents {
	
	private static boolean hasReflected = false;
	
	@SubscribeEvent
	public static void onChunkGen(ChunkGeneratorEvent event) {
		if(hasReflected)
			return;
		
		IChunkGenerator generator = event.getGen();
		
		if(!(generator instanceof ChunkGeneratorOverworld))
			return;
		
		MapGenBase newCaves = new BiggerCave();
		MapGenBase oldCaves = null;
		try {
			//Would have used ReflectionHelper if Eclipse didn't select the wrong bloody overloaded method
			Field f = null;
			if((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"))
				f= FieldUtils.getDeclaredField(ChunkGeneratorOverworld.class, "caveGenerator", true);
			else
				f = FieldUtils.getDeclaredField(ChunkGeneratorOverworld.class, "field_186003_v", true);
			
			f.setAccessible(true);
			newCaves = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(newCaves, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
			f.set(generator, newCaves);
			
			UnknownTweaks.INSTANCE.log.info("Sucessfully reflected caveGenerator");
			
			hasReflected = true;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			//If anything happens, we didn't do 'nothing
			hasReflected = true;
			return;
		}
	}

}
