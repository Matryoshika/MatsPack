package matryoshika.unknowntweaks.bouncer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CredentialStorage implements IStorage<ICredential>{

	@Override
	public NBTBase writeNBT(Capability<ICredential> capability, ICredential instance, EnumFacing side) {
		NBTTagLong tag = new NBTTagLong(instance.diedAt());
		return null;
	}

	@Override
	public void readNBT(Capability<ICredential> capability, ICredential instance, EnumFacing side, NBTBase nbt) {
		instance.setDied(((NBTTagLong)nbt).getLong());
	}

}
