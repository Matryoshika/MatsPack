package matryoshika.unknowntweaks.packets;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class UnknownTweaksPacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(UnknownTweaks.MODID);

	
	public static void registerPackets() {
		int id = 0;
		INSTANCE.registerMessage(RemoveBlockOnClient.RemoveBlockOnClientHandler.class, RemoveBlockOnClient.class, id++, Side.CLIENT);
	}
}
