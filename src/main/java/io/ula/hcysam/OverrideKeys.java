package io.ula.hcysam;

import io.ula.hcysam.lottery.lotteryresults.StuckLotteryResult;

public class OverrideKeys {
    public static int LotteryResult_STUCK_RESULT_StuckTime;
    public static long SERVERTICK_DD=0,SERVERTICK_HH=0,SERVERTICK_MM=0,SERVERTICK_SS=0;
    public static String SERVERTICK_LDT;
    public static void updateKeys(){
        LotteryResult_STUCK_RESULT_StuckTime = StuckLotteryResult.nn;
    }
}
