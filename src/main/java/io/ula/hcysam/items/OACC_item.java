package io.ula.hcysam.items;

import io.ula.hcysam.listeners.ServerTickListener;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OACC_item extends Item {
    private final String OACC_ID;
    public  OACC_item(FabricItemSettings settings,String id){
        super(settings);
        OACC_ID = id;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(),player, ClientboundSetTitlesPacket.Type.ACTIONBAR,new TextComponent("请立即找到腐竹或管理员以兑换").withStyle(ChatFormatting.GOLD)
                .append(new TextComponent(" 没有人知道你抽到了这个！").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)));
        super.use(level,player,interactionHand);
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        list.add(new TextComponent("正版账号ID：").
                append(new TextComponent(OACC_ID).withStyle(ChatFormatting.GOLD)));
    }
}
