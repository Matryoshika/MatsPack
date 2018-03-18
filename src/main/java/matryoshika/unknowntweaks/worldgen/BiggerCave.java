package matryoshika.unknowntweaks.worldgen;

import java.util.Random;

import com.google.common.base.MoreObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BiggerCave extends MapGenCaves {

	@Override
	protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn)
    {
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7) != 0)
        {
            i = 0;
        }

        for (int j = 0; j < i; ++j)
        {
            double d0 = (double)(chunkX * 16 + this.rand.nextInt(16));
            double d1 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = (double)(chunkZ * 16 + this.rand.nextInt(16));
            int k = 2;

            if (this.rand.nextInt(2) == 0)
            {
                this.addRoom(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2);
                k += this.rand.nextInt(2); // 4
            }

            for (int l = 0; l < k; ++l)
            {
                float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
                
                f2 *= 2.25;

                f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;

                this.addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

}
