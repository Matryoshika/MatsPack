package matryoshika.unknowntweaks.bouncer;

import matryoshika.unknowntweaks.UnknownTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CredentialProvider implements ICapabilitySerializable<NBTTagLong>{
	
	@CapabilityInject(ICredential.class)
	public static final Capability<ICredential> CREDENTIAL_CAP = null;
	
	private ICredential INSTANCE = CREDENTIAL_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CREDENTIAL_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CREDENTIAL_CAP ? CREDENTIAL_CAP.cast(INSTANCE) : null;
	}

	@Override
	public NBTTagLong serializeNBT() {
		return (NBTTagLong) CREDENTIAL_CAP.getStorage().writeNBT(CREDENTIAL_CAP, INSTANCE, null);
	}

	@Override
	public void deserializeNBT(NBTTagLong nbt) {
		CREDENTIAL_CAP.getStorage().readNBT(CREDENTIAL_CAP, INSTANCE, null, nbt);
	}
	
	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		//if(event.getObject() instanceof EntityPlayer)
			//event.addCapability(new ResourceLocation(UnknownTweaks.MODID + CREDENTIAL_CAP.getName()), new CredentialProvider());
	}

}
