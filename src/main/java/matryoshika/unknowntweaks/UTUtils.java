package matryoshika.unknowntweaks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class UTUtils {

	public static AxisAlignedBB getBB(BlockPos pos, int range) {
		return new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range);
	}
	
	
	public static boolean isInside(BlockPos toCheck, Collection<BlockPos> collection) {
		if(collection.stream().filter(pos -> toCheck.toLong() == pos.toLong()).count() == 1)
			return true;
		return false;
	}
	
	public static boolean isInside(BlockPos toCheck, Iterable<BlockPos> collection) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		collection.forEach(list::add);
		return isInside(toCheck, list);
	}

}
