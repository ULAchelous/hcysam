package io.ula.hcysam.lottery;

import java.util.ArrayList;
import java.util.Random;

import static io.ula.hcysam.lottery.LotteryResults.*;

public class LotteryEngine {
    private static final String[] ONLINE_PLAYERS = new String[] {
            "Kenny_Gu_Gua","_Achelous","Kenny_Gu_Gua"
    };
    private static final String[] OFFLINE_PLAYERS = new String[]{
            "zyl","LHH","machine","fudima"
    };

    public static LotteryResult getResult(){
        int result_step;
        ArrayList<LotteryResult> lotteryResults = getOrCreateResultsList();
        result_step = new Random().nextInt(lotteryResults.size()-1);
        return lotteryResults.get(result_step);
    }
}
