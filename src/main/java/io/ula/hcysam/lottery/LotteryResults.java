package io.ula.hcysam.lottery;


import io.ula.hcysam.ItemListProvider;
import io.ula.hcysam.lottery.lotteryresults.StuckLotteryResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class LotteryResults {
    private static ArrayList<LotteryResult> lotteryResults = new ArrayList<>();
    public static final LotteryResult STUCK_RESULT = new StuckLotteryResult(2,"客户端卡顿");
    public static final LotteryResult PRIZE_RESULT = new LotteryResult(2,"物品补给"){
        @Override
        public void serverBehavior(Player player, Component subtitle) {
            super.serverBehavior(player, subtitle);
            int id = new Random().nextInt(ItemListProvider.getOrCreateItemList().size()-1);
            ItemStack stack = new ItemStack(ItemListProvider.getOrCreateItemList().get(id),64);
            player.setItemSlot(EquipmentSlot.MAINHAND,stack);
        }
    };
    public static ArrayList<LotteryResult> getOrCreateResultsList() {
        if (Objects.equals(lotteryResults, new ArrayList<LotteryResult>())) {
            listAddItem(STUCK_RESULT);
            listAddItem(PRIZE_RESULT);
        }
        return lotteryResults;
    }
    private static void listAddItem(LotteryResult lr){
        for (int i = 1; i <= lr.getWeight(); i++)
            lotteryResults.add(lr);
    }
}
