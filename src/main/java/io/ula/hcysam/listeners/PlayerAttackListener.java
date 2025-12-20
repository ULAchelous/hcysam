package io.ula.hcysam.listeners;

import io.ula.hcysam.Registry_init;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class PlayerAttackListener {
    public static void register(){
        AttackEntityCallback.EVENT.register((player, level, interactionHand, entity, entityHitResult) -> {
            if(player.isHolding(Registry_init.CRYSTALHANIWA)) {
                if (entity.getType() != player.getType()) {
                    player.sendMessage(new TextComponent("达咩"), UUID.randomUUID());
                    return InteractionResult.SUCCESS;
                }
                if(!player.hasEffect(Registry_init.XY_EFFECT_1)&&!player.hasEffect(Registry_init.XY_EFFECT_2)&&!player.hasEffect(Registry_init.XY_EFFECT_3)){
                    player.playSound(Registry_init.VOICE_XY_1,1,1);
                    player.sendMessage(new TextComponent("干，干嘛……？！"), UUID.randomUUID());
                    player.addEffect(new MobEffectInstance(Registry_init.XY_EFFECT_1,1000));

                }else if(!player.hasEffect(Registry_init.XY_EFFECT_2)&&!player.hasEffect(Registry_init.XY_EFFECT_3)){
                    player.playSound(Registry_init.VOICE_XY_2,1,1);
                    player.sendMessage(new TextComponent("（诶，诶……这这这，莫非是……？！）"),UUID.randomUUID());
                    player.removeEffect(Registry_init.XY_EFFECT_1);
                    player.addEffect(new MobEffectInstance(Registry_init.XY_EFFECT_2,1000));
                }else{
                    if(player.hasEffect(Registry_init.XY_EFFECT_3)){
                        player.removeEffect(Registry_init.XY_EFFECT_3);
                        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,1500,255,false,true,true));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1500,5,false,true,true));
                        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,1500,255,false,true,true));
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,1500,255,false,true,true));
                        return InteractionResult.SUCCESS;
                    }
                    player.playSound(Registry_init.VOICE_XY_3,1,1);
                    player.sendMessage(new TextComponent("（等等……照这样下去，我和老师，难道要……？！）"),UUID.randomUUID());
                    player.removeEffect(Registry_init.XY_EFFECT_2);
                    player.addEffect(new MobEffectInstance(Registry_init.XY_EFFECT_3,1000));
                }
            }
            return InteractionResult.PASS;
        });
    }
}
