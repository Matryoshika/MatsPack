package matryoshika.unknowntweaks.crafttweaker;

import java.util.List;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlockState;
import crafttweaker.mc1120.block.MCBlockState;
import crafttweaker.zenscript.IBracketHandler;
import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionInt;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.ZenType;
import stanhebben.zenscript.type.natives.IJavaMethod;

@BracketHandler(priority = 10)
@ZenRegister
public class BracketHandlerBlockState implements IBracketHandler {

	public static IJavaMethod method = CraftTweakerAPI.getJavaMethod(BracketHandlerBlockState.class, "getBlockState", String.class, String.class, String.class, int.class);

	public static IBlockState getBlockState(String stateVerifier, String modid, String id, int meta) {
		Block value = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modid, id));

		return !stateVerifier.equals("state") ? null : value == null ? null : new MCBlockState(value.getStateFromMeta(meta));
	}

	@Override
	public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
		if (tokens != null && tokens.size() > 6)
			return position -> new ExpressionCallStatic(position, environment, method, new ExpressionString(position, tokens.get(0).getValue()), new ExpressionString(position, tokens.get(2).getValue()), new ExpressionString(position, tokens.get(4).getValue()),new ExpressionInt(position, Integer.parseInt(tokens.get(6).getValue()), ZenType.INT));
		else
			return null;
	}
}
