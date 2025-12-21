package io.ula.hcysam.listeners;

import io.ula.hcysam.OverrideKeys;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ServerStoppedListener {
    public static void register(){
        ServerLifecycleEvents.SERVER_STOPPED.register(minecraftServer -> {
            minecraftServer.getScoreboard().resetPlayerScore(String.format("§e§l现在时间：§r§c%s", OverrideKeys.SERVERTICK_LDT),minecraftServer.getScoreboard().getObjective("dr-sb"));
            minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e天",OverrideKeys.SERVERTICK_DD),minecraftServer.getScoreboard().getObjective("dr-sb"));
            minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e小时",OverrideKeys.SERVERTICK_HH),minecraftServer.getScoreboard().getObjective("dr-sb"));
            minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e分钟",OverrideKeys.SERVERTICK_MM),minecraftServer.getScoreboard().getObjective("dr-sb"));
            minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e秒",OverrideKeys.SERVERTICK_SS),minecraftServer.getScoreboard().getObjective("dr-sb"));
        });
    }
}
