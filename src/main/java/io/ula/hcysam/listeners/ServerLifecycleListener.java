package io.ula.hcysam.listeners;

import io.ula.hcysam.server.ScoreBoardHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.apache.logging.log4j.spi.LoggerRegistry;

import java.util.Timer;
import java.util.TimerTask;

import static io.ula.hcysam.HCYmod.LOGGER;

public class ServerLifecycleListener {
    public static void register(){
        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> {
            Objective obj = ScoreBoardHelper.getScoreboardObj(minecraftServer.getScoreboard(),"dr-sb",new TextComponent("希望之地 - 跨年抽奖").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
            minecraftServer.getScoreboard().setDisplayObjective(1,ScoreBoardHelper.getScoreboardObj(minecraftServer.getScoreboard(),"dr-sb",new TextComponent("希望之地 - 跨年抽奖").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD)));
        });
    }
}
