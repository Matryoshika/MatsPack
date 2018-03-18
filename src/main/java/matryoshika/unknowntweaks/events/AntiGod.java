package matryoshika.unknowntweaks.events;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class AntiGod {

	/**
	 * Anything that adds god-mode should be running at
	 * {@link EventPriority#HIGHEST}. We're just gonna trail behind them and undo
	 * what they did.
	 */
	@SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGH)
	public static void damageForAll(LivingHurtEvent event) {
		if (event.isCanceled())
			event.setCanceled(false);
	}

	/**
	 * Anything that adds god-mode should be running at
	 * {@link EventPriority#HIGHEST}. We're just gonna trail behind them and undo
	 * what they did.
	 */
	@SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGH)
	public static void deathForAll(LivingDeathEvent event) {
		if (event.isCanceled())
			event.setCanceled(false);
	}

}
