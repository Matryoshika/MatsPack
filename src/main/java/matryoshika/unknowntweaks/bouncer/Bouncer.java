package matryoshika.unknowntweaks.bouncer;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;


@Mod.EventBusSubscriber
public class Bouncer {
	
	public static Map<UUID, Boolean> hardcoreMap = new HashMap<UUID, Boolean>();
	
	@SideOnly(Side.SERVER)
	private static List<UUID> playersToKick = new ArrayList<UUID>();

	/**
	 * Checks the players' capability data and sees if they've died recently
	 * If so, adds them to the playersToKick list
	 */
	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {
		//Auto-add for testing
		playersToKick.add(event.player.getUniqueID());
		
		FMLServerHandler.instance().getServer().addScheduledTask(() -> {});
	}
	
	/**
	 * Used to actually kick the players.
	 * Needs to be done the tick after PlayerLoggedInEvent
	 * as the EntityPlayer is still being constructed.
	 */
	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public static void onServerTick(ServerTickEvent event) {
		if(playersToKick.isEmpty()) 
			return;
		
		if(event.phase == TickEvent.Phase.END)
			return;
		
		
		playersToKick.forEach(uuid -> {
			FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUUID(uuid).connection.disconnect(new TextComponentTranslation("Thank you, come again"));
		});
		
		playersToKick.clear();
	}

}
