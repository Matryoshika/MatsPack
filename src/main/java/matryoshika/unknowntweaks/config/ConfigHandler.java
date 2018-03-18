package matryoshika.unknowntweaks.config;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraftforge.common.config.Config;

@Config(modid = UnknownTweaks.MODID)
public class ConfigHandler {
	
	@Config.Comment("How many hours a player will be unable to play for after having died; min. 1h max. 48h. Is overriden if the player chooses hardcore-death")
	public static int deathCooldown = 12;
	
}
