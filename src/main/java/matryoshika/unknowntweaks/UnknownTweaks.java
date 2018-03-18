package matryoshika.unknowntweaks;

import org.apache.logging.log4j.Logger;

import matryoshika.unknowntweaks.bouncer.Credential;
import matryoshika.unknowntweaks.bouncer.CredentialStorage;
import matryoshika.unknowntweaks.bouncer.ICredential;
import matryoshika.unknowntweaks.content.TileBasicPickUpper;
import matryoshika.unknowntweaks.crafttweaker.CraftTweakerIntegration;
import matryoshika.unknowntweaks.crafttweaker.Salter;
import matryoshika.unknowntweaks.packets.UnknownTweaksPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = UnknownTweaks.MODID, version = UnknownTweaks.VERSION)
public class UnknownTweaks {
	public static final String MODID = "unknowntweaks";
	public static final String VERSION = "1.0";

	public Logger log = null;

	@Instance
	public static UnknownTweaks INSTANCE;

	@SidedProxy(clientSide = "matryoshika.unknowntweaks.ClientProxy", serverSide = "matryoshika.unknowntweaks.ServerProxy")
	public static Proxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log = event.getModLog();

		if (Loader.isModLoaded("crafttweaker"))
			CraftTweakerIntegration.init();

		log.warn(Salter.getSalted("minecraft:diamond_block"));
		log.warn(Salter.getSalted(Blocks.DIAMOND_BLOCK.getRegistryName().toString()));

		CapabilityManager.INSTANCE.register(ICredential.class, new CredentialStorage(), Credential.class);
		UnknownTweaksPacketHandler.registerPackets();
		GameRegistry.registerTileEntity(TileBasicPickUpper.class, new ResourceLocation(MODID, "tile_basic_pickupper").toString());
		GameRegistry.registerTileEntity(TileBasicPickUpper.class, new ResourceLocation(MODID, "tile_mediocre_pickupper").toString());
		GameRegistry.registerTileEntity(TileBasicPickUpper.class, new ResourceLocation(MODID, "tile_super_pickupper").toString());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
