package io.ula.hcysam.lottery;

import io.ula.hcysam.listeners.ServerTickListener;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.ula.hcysam.HCYmod.MOD_ID;

public class LotteryResult {
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private final long  WEIGHT;
    private final String NAME;
    public LotteryResult(long w,String name){
        WEIGHT = w;
        NAME = name;
    }
    public long getWeight(){
        return WEIGHT;
    }
    public void clientBehavior(Player player){}
    public void serverBehavior(Player player,Component subtitle){
        if(ServerTickListener.getNullableServer() != null){
            ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(), ClientboundSetTitlesPacket.Type.TITLE,new TextComponent("恭喜").withStyle(ChatFormatting.GREEN)
                    .append(new TextComponent("").append(player.getName()).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD))
                    .append(new TextComponent("抽中").withStyle(ChatFormatting.GREEN))
                    .append(new TextComponent(NAME).withStyle(ChatFormatting.DARK_AQUA))
            );
            ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(),player, ClientboundSetTitlesPacket.Type.SUBTITLE,subtitle);
        }
    }
}
