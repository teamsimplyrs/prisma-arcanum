package com.teamsimplyrs.prismaarcanum.particle.particleOptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class IgnisParticleOptions implements ParticleOptions {
    public static final Codec<IgnisParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockPos.CODEC.fieldOf("destination").forGetter((options) -> {
            return options.destination;
        }), Codec.INT.fieldOf("arrival_in_ticks").forGetter((options) -> {
            return options.arrivalInTicks;
        })).apply(instance, IgnisParticleOptions::new);
    });
    public static final ParticleOptions.Deserializer<IgnisParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<IgnisParticleOptions>() {
        public IgnisParticleOptions fromCommand(ParticleType<IgnisParticleOptions> options, StringReader commandString) throws CommandSyntaxException {
            commandString.expect(' ');
            float f = (float)commandString.readDouble();
            commandString.expect(' ');
            float f1 = (float)commandString.readDouble();
            commandString.expect(' ');
            float f2 = (float)commandString.readDouble();
            commandString.expect(' ');
            int i = commandString.readInt();
            BlockPos blockpos = BlockPos.containing((double)f, (double)f1, (double)f2);
            return new IgnisParticleOptions(blockpos, i);
        }

        public IgnisParticleOptions fromNetwork(ParticleType<IgnisParticleOptions> p_175862_, FriendlyByteBuf pBuffer) {
            BlockPos position = pBuffer.readBlockPos();
            int i = pBuffer.readVarInt();
            return new IgnisParticleOptions(position, i);
        }
    };
    private final BlockPos destination;
    private final int arrivalInTicks;

    public IgnisParticleOptions(BlockPos destinationPos, int travelTime) {
        this.destination = destinationPos;
        this.arrivalInTicks = travelTime;
    }

    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeBlockPos(this.destination);
        pBuffer.writeVarInt(this.arrivalInTicks);
    }

    public String writeToString() {
        double d0 = this.destination.getX();
        double d1 = this.destination.getY();
        double d2 = this.destination.getZ();
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", PAParticles.IGNIS_PARTICLES.getId(), d0, d1, d2, this.arrivalInTicks);
    }

    public ParticleType<IgnisParticleOptions> getType() {
        return PAParticles.IGNIS_PARTICLES.get();
    }

    public BlockPos getDestination() {
        return this.destination;
    }

    public int getArrivalInTicks() {
        return this.arrivalInTicks;
    }
}
