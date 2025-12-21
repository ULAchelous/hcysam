package io.ula.hcysam.lottery.lotteryresults;

import io.ula.hcysam.listeners.ServerTickListener;
import io.ula.hcysam.lottery.LotteryResult;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class StuckLotteryResult extends LotteryResult {
    public static  int nn = new Random().nextInt(15);
    public StuckLotteryResult(long w,String name){
        super(w,name);
    }
    public void clientBehavior(Player player) {
        //
    }

    public void serverBehavior(Player player, Component subtitle){
        super.serverBehavior(player,new TextComponent(String.format("客户端卡顿§c§l%d§r§e秒 ",nn)).append(subtitle));
        if(ServerTickListener.getNullableServer() != null){
            for(int i = nn;i>=0;i--){
                ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(), player, ClientboundSetTitlesPacket.Type.TITLE, new TextComponent(String.format("%d",i)).withStyle(ChatFormatting.RED));
                ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(), player,ClientboundSetTitlesPacket.Type.SUBTITLE, new TextComponent(String.format("感谢")).withStyle(ChatFormatting.YELLOW).append(player.getName()).withStyle(ChatFormatting.DARK_AQUA).append("带来的卡顿").withStyle(ChatFormatting.YELLOW));
                try {
                    Thread.sleep(3000);
                }catch(Exception e){
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }
}
