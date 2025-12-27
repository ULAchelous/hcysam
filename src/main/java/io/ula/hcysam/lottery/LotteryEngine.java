package io.ula.hcysam.lottery;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static io.ula.hcysam.lottery.LotteryResults.*;

public class LotteryEngine {
    private static final String[] ONLINE_PLAYERS = new String[] {
            "Kenny_Gu_Gua","_Achelous","Kenny_Gu_Gua"
    };
    private static final String[] OFFLINE_PLAYERS = new String[]{
            "zyl","LHH","machine","fudima"
    };
    public static LotteryResult getResult(Player player){
        Boolean is_online=false;
        for(int i=0;i< ONLINE_PLAYERS.length;i++){
            if(player.getName().getString() == ONLINE_PLAYERS[i]){
                is_online=true;
                break;
            }
        }
        while(true){
            int result_step;
            ArrayList<LotteryResult> lotteryResults = createResultsList();
            result_step = new Random().nextInt(lotteryResults.size()-1);
            if(lotteryResults.get(result_step).isEnabled()){
                if(is_online){//如果玩家是正版账号
                    if(lotteryResults.get(result_step) != OACC_RESULT)//如果不是正版账号兑换券就返回抽奖结果
                        return lotteryResults.get(result_step);
                    else continue;//否则再次取随机数
                }else{
                    return lotteryResults.get(result_step);
                }
            }
        }
    }
}
