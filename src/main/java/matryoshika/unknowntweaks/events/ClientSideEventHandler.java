package matryoshika.unknowntweaks.events;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = UnknownTweaks.MODID)
public class ClientSideEventHandler {

	@SubscribeEvent
	public static void registerRenderers(ModelRegistryEvent event) {
	}

}
