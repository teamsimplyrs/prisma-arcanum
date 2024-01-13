package com.teamsimplyrs.prismaarcanum.particle.particleOptions;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;
import java.util.Locale;

public class IgnisParticleOptions implements ParticleOptions {
    public static final Codec<IgnisParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.DOUBLE.listOf().xmap((doubleList)->{return new Vec3(doubleList.get(0), doubleList.get(1), doubleList.get(2));},(vector)->{
            return Arrays.asList(vector.x, vector.y, vector.z);
        }).fieldOf("destination").forGetter((options) -> {
            return options.destination;
        }), Codec.INT.fieldOf("arrival_in_ticks").forGetter((options) -> {
            return options.arrivalInTicks;
        })).apply(instance, IgnisParticleOptions::new);
    });
    public static final ParticleOptions.Deserializer<IgnisParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<IgnisParticleOptions>() {
        public IgnisParticleOptions fromCommand(ParticleType<IgnisParticleOptions> options, StringReader commandString) throws CommandSyntaxException {
            commandString.expect(' ');
            double d = commandString.readDouble();
            commandString.expect(' ');
            double d1 = commandString.readDouble();
            commandString.expect(' ');
            double d2 = commandString.readDouble();
            commandString.expect(' ');
            int i = commandString.readInt();
            Vec3 position = new Vec3(d,d1,d2);
            return new IgnisParticleOptions(position, i);
        }

        public IgnisParticleOptions fromNetwork(ParticleType<IgnisParticleOptions> p_175862_, FriendlyByteBuf pBuffer) {
            double x = pBuffer.readDouble();
            double y = pBuffer.readDouble();
            double z = pBuffer.readDouble();
            Vec3 position = new Vec3(x,y,z);
            int i = pBuffer.readVarInt();
            return new IgnisParticleOptions(position, i);
        }
    };
    private final Vec3 destination;
    private final int arrivalInTicks;

    public IgnisParticleOptions(Vec3 destinationPos, int travelTime) {
        this.destination = destinationPos;
        this.arrivalInTicks = travelTime;
    }

    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeDouble(this.destination.x);
        pBuffer.writeDouble(this.destination.y);
        pBuffer.writeDouble(this.destination.z);
        pBuffer.writeVarInt(this.arrivalInTicks);
    }

    public String writeToString() {
        double d0 = this.destination.x;
        double d1 = this.destination.y;
        double d2 = this.destination.z;
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %d", PAParticles.IGNIS_PARTICLES.getId(), d0, d1, d2, this.arrivalInTicks);
    }

    public ParticleType<IgnisParticleOptions> getType() {
        return PAParticles.IGNIS_PARTICLES.get();
    }

    public Vec3 getDestination() {
        return this.destination;
    }

    public int getArrivalInTicks() {
        return this.arrivalInTicks;
    }
}
