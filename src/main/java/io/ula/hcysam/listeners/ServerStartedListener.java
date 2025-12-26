package io.ula.hcysam.listeners;

import io.ula.hcysam.server.ScoreBoardHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

import java.awt.*;

public class ServerStartedListener {
    public static void register(){
        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> {
            Objective sb_obj = ScoreBoardHelper.getScoreboardObj(minecraftServer.getScoreboard(),"dr-sb", new TextComponent("Desideraregio - 跨年抽奖"));
            minecraftServer.getScoreboard().setDisplayObjective(1,sb_obj);
        });
    }
}
