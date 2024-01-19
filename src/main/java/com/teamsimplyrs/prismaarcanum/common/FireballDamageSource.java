package com.teamsimplyrs.prismaarcanum.common;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class FireballDamageSource extends DamageSource {
    public FireballDamageSource(Holder<DamageType> pType, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        super(pType, pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public FireballDamageSource(Holder<DamageType> pType, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity) {
        super(pType, pDirectEntity, pCausingEntity);
    }

    public FireballDamageSource(Holder<DamageType> pType, Vec3 pDamageSourcePosition) {
        super(pType, pDamageSourcePosition);
    }

    public FireballDamageSource(Holder<DamageType> pType, @Nullable Entity pEntity) {
        super(pType, pEntity);
    }

    public FireballDamageSource(Holder<DamageType> pType) {
        super(pType);
    }

}
