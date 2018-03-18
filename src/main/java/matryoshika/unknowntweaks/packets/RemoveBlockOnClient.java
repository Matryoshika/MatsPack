package matryoshika.unknowntweaks.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RemoveBlockOnClient implements IMessage{
	
	public RemoveBlockOnClient() {}
	
	public BlockPos target;
	public IBlockState state;

	@Override
	public void fromBytes(ByteBuf buf) {
		target = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		state = Block.getStateById(buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(target.getX());
		buf.writeInt(target.getY());
		buf.writeInt(target.getZ());
		buf.writeInt(Block.getStateId(state));
	}
	
	public RemoveBlockOnClient setPos(BlockPos pos) {
		target = pos;
		return this;
	}
	
	public RemoveBlockOnClient setState(IBlockState st) {
		state = st;
		return this;
	}
	
	public static class RemoveBlockOnClientHandler implements IMessageHandler<RemoveBlockOnClient, IMessage> {

		@Override
		public IMessage onMessage(RemoveBlockOnClient message, MessageContext ctx) {
			
			Minecraft.getMinecraft().addScheduledTask(() ->{
				Minecraft.getMinecraft().world.setBlockState(message.target, message.state);
			});
			return null;
		}

	}

}
