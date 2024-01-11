package com.teamsimplyrs.prismaarcanum.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class PAAnimationDefinitions {

    // From BlockBench, export animation as text file and copy-paste the contents for each different animation here as a separate AnimationDefinition object

    public static final AnimationDefinition FIREBALL_PROJECTILE_ANIMATION = AnimationDefinition.Builder.withLength(2f).looping()
            .addAnimation("all",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(360f, 360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
}
