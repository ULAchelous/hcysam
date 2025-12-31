package io.ula.hcysam;

import io.ula.hcysam.blocks.Lottery_block;
import io.ula.hcysam.items.Crystalhaniwa;
import io.ula.hcysam.effects.XY_Effect;
import io.ula.hcysam.items.Delta_ticket;
import io.ula.hcysam.items.MaoQuotes;
import io.ula.hcysam.items.OACC_item;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.scores.Scoreboard;
import org.lwjgl.system.CallbackI;


import static io.ula.hcysam.HCYmod.MOD_ID;


public class Registry_init {
    public static final XY_Effect XY_EFFECT_1 =register(1, "xy_1", (XY_Effect) new XY_Effect(MobEffectCategory.BENEFICIAL,0xFFC0CB).addAttributeModifier(Attributes.MOVEMENT_SPEED, "A5C29269-89DA-43CB-AA07-E639C081A31F", (double)0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.JUMP_STRENGTH,"FE00E407-003A-4D41-A163-F4B01336C9D6",2f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final XY_Effect XY_EFFECT_2 =register(1, "xy_2", (XY_Effect) new XY_Effect(MobEffectCategory.BENEFICIAL,0xFFC0CB).addAttributeModifier(Attributes.MOVEMENT_SPEED, "E9965290-57AD-4D4A-B16C-E1EF8D5CE093", (double)0.6F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.JUMP_STRENGTH,"DCE1568E-7041-43BA-9C6F-33C9F791577F",4f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final XY_Effect XY_EFFECT_3 =register(1, "xy_3", (XY_Effect) new XY_Effect(MobEffectCategory.BENEFICIAL,0xFFC0CB).addAttributeModifier(Attributes.MOVEMENT_SPEED, "15BF3372-450A-4E26-A784-C81B9910F133", (double)0.9F, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(Attributes.JUMP_STRENGTH,"B79C67A6-8EF9-4FD8-B881-D045EBB08616",6f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    //Custom MobEffects

    public static final Crystalhaniwa CRYSTALHANIWA = register("crystalhaniwa", new Crystalhaniwa(Crystalhaniwa.t,1,1f,new FabricItemSettings()));
    public static final Delta_ticket DELTA_TICKET = register("delta_ticket",new Delta_ticket(new FabricItemSettings().maxCount(20)));
    public static final OACC_item OACC_TICKET_1 = register("oacc_ticket_1",new OACC_item(new FabricItemSettings().maxCount(1),"0x18e9b5d"));
    public static final OACC_item OACC_TICKET_2 = register("oacc_ticket_2",new OACC_item(new FabricItemSettings().maxCount(1),"0x18e9b5e"));
    public static final MaoQuotes MAO_QUOTES = register("mao_quotes",new MaoQuotes(new FabricItemSettings().maxCount(1)));
    //Items

    public static final Lottery_block LOTTERY_BLOCK = register("lottery_block",new Lottery_block(FabricBlockSettings.of(Material.STONE).strength(-1f)),new FabricItemSettings());
    //Blocks

    public static final SoundEvent VOICE_XY_1 = register("voice_xy_1");
    public static final SoundEvent VOICE_XY_2 = register("voice_xy_2");
    public static final SoundEvent VOICE_XY_3 = register("voice_xy_3");
    public static final SoundEvent VOICE_TITLE = register("voice_title");
    //SoundEvents

    public static final CreativeModeTab TAB_HCYSAM = FabricItemGroupBuilder.create(new ResourceLocation(MOD_ID,"tab_hcysam"))
            .icon(() ->new ItemStack(LOTTERY_BLOCK))
            .appendItems(itemStacks -> {
                    itemStacks.add(new ItemStack(Registry_init.CRYSTALHANIWA));
                    itemStacks.add(new ItemStack(Registry_init.LOTTERY_BLOCK));
                    itemStacks.add(new ItemStack(Registry_init.DELTA_TICKET));
                    itemStacks.add(new ItemStack(Registry_init.MAO_QUOTES));
                }
            )
            .build();
    //ItemGroups
    public static <T extends Item> T register(String path,T item){
        return Registry.register(Registry.ITEM,String.format("%s:%s",MOD_ID,path),item);
    }//Item register
    public static <T extends Block> T register(String path,T block,FabricItemSettings Custom_Item_Settings){
        Registry.register(Registry.BLOCK,String.format("%s:%s",MOD_ID,path), block);
        Registry.register(Registry.ITEM,String.format("%s:%s",MOD_ID,path),new BlockItem(block,Custom_Item_Settings));
        return block;
    }//Block register
    private static <T extends MobEffect> T register(int i, String path, T mobEffect) {
        return Registry.registerMapping(Registry.MOB_EFFECT, i, String.format("%s:%s",MOD_ID,path), mobEffect);
    }//MobEffect register
    private static <T extends SoundEvent> SoundEvent register(String path) {
        return Registry.register(Registry.SOUND_EVENT,new ResourceLocation(String.format("%s:%s",MOD_ID,path)),new SoundEvent(new ResourceLocation(String.format("%s:%s",MOD_ID,path))));
    }//SoundEvent register

    static void initialize() {
        //make it empty
        //class initializer
    }
}
