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
    public static  int nn = new Random().nextInt(60);
    public StuckLotteryResult(long w,String name){
        super(w,name,true);
    }

    public void serverBehavior(Player player, Component subtitle){
        super.serverBehavior(player,new TextComponent(String.format("服务器趋势§c§l%d§r§e秒 ",nn)));
        try {
            Thread.sleep((long)nn*1000);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
