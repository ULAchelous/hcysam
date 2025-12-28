package io.ula.hcysam.lottery;


import io.ula.hcysam.ItemListProvider;
import io.ula.hcysam.OverrideKeys;
import io.ula.hcysam.Registry_init;
import io.ula.hcysam.listeners.ServerTickListener;
import io.ula.hcysam.lottery.lotteryresults.StuckLotteryResult;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

import java.util.*;

public class LotteryResults {
    private static ArrayList<LotteryResult> lotteryResults = new ArrayList<>();
    public static final LotteryResult STUCK_RESULT = new StuckLotteryResult(3,"卡顿");
    public static final LotteryResult PRIZE_RESULT = new LotteryResult(5,"物品补给",true){
        @Override
        public void serverBehavior(Player player, Component subtitle) {
            super.serverBehavior(player, subtitle);
            int id = new Random().nextInt(ItemListProvider.getOrCreateItemList().size()-1);
            ItemStack stack = new ItemStack(ItemListProvider.getOrCreateItemList().get(id),64);
            player.setItemSlot(EquipmentSlot.OFFHAND,stack);
        }
    };
    public static final LotteryResult OACC_RESULT = new LotteryResult(3,"§k正版账号",true){
        @Override
        public void clientBehavior(Player player) {
            super.clientBehavior(player);
        }

        @Override
        public void serverBehavior(Player player, Component subtitle) {
            super.serverBehavior(player, new TextComponent("使用兑换券右键以查看信息"));
            ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(),player,ClientboundSetTitlesPacket.Type.TITLE,new TextComponent("恭喜").withStyle(ChatFormatting.GREEN)
                    .append(new TextComponent("你").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD))
                    .append(new TextComponent("抽中").withStyle(ChatFormatting.GREEN))
                    .append(new TextComponent("正版账号").withStyle(ChatFormatting.DARK_AQUA))
            );
            ItemStack stack1 = new ItemStack(Registry_init.OACC_TICKET_1,1);
            ItemStack stack2 = new ItemStack(Registry_init.OACC_TICKET_2,1);
            if(OverrideKeys.OACC_1_HAS_BEEN_USED) {
                player.setItemSlot(EquipmentSlot.OFFHAND, stack2);
                this.disable();
            }else{
                player.setItemSlot(EquipmentSlot.OFFHAND,stack1);
                OverrideKeys.OACC_1_HAS_BEEN_USED = true;
            }
        }
    };
    public static final LotteryResult HANIWA_RESULT = new LotteryResult(5,"植轮",true){
        @Override
        public void serverBehavior(Player player, Component subtitle) {
            super.serverBehavior(player, subtitle);
            ItemStack stack = new ItemStack(Registry_init.CRYSTALHANIWA,1);
            player.setItemSlot(EquipmentSlot.OFFHAND,stack);
        }
    };
    public static final LotteryResult CREATIVE_RESULT = new LotteryResult(4,"创造",true){
        @Override
        public void serverBehavior(Player player, Component subtitle) {
            int ni = new Random().nextInt(1200);
            super.serverBehavior(player,new TextComponent("创造模式").withStyle(ChatFormatting.GOLD).append(new TextComponent(String.format("%d",ni)).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)).append("秒"));
            player.setGameMode(GameType.CREATIVE);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    player.setGameMode(GameType.SURVIVAL);
                }
            },ni*1000);
        }
    };
    public static ArrayList<LotteryResult> createResultsList() {
        lotteryResults = new ArrayList<LotteryResult>();
           listAddItem(STUCK_RESULT);
           listAddItem(PRIZE_RESULT);
           listAddItem(HANIWA_RESULT);
           listAddItem(OACC_RESULT);
           listAddItem(CREATIVE_RESULT);
        return lotteryResults;
    }
    private static void listAddItem(LotteryResult lr){
        for (int i = 1; i <= lr.getWeight(); i++)
            lotteryResults.add(lr);
    }
}
