package matryoshika.unknowntweaks.worldgen;

import java.util.ArrayList;
import java.util.List;

import matryoshika.unknowntweaks.packets.RemoveBlockOnClient;
import matryoshika.unknowntweaks.packets.UnknownTweaksPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class BlockRemover implements IWorldEventListener {

	public final List<Block> blocksToRemove = new ArrayList<Block>(Arrays.asList(new Block[] { Blocks.END_PORTAL_FRAME, Blocks.FURNACE }));

	@Override
	public void notifyBlockUpdate(World world, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
	}

	@Override
	public void notifyLightSet(BlockPos pos) {

	}

	@Override
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
	}

	@Override
	public void playSoundToAllNearExcept(EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {

	}

	@Override
	public void playRecord(SoundEvent soundIn, BlockPos pos) {

	}

	@Override
	public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

	}

	@Override
	public void spawnParticle(int id, boolean ignoreRange, boolean p_190570_3_, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {

	}

	@Override
	public void onEntityAdded(Entity entity) {

	}

	@Override
	public void onEntityRemoved(Entity entityIn) {

	}

	@Override
	public void broadcastSound(int soundID, BlockPos pos, int data) {

	}

	@Override
	public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {
		
	}

	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {

	}

	public boolean playersNearby(World world, BlockPos pos) {
		return world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 8, false) == null ? false : true;
	}

}
