package io.ula.hcysam.listeners;

import io.ula.hcysam.OverrideKeys;
import io.ula.hcysam.server.ScoreBoardHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import static io.ula.hcysam.HCYmod.MOD_ID;

public class ServerTickListener {

    private static MinecraftServer MC_SERVER_OBJ;
    private static final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static Boolean flag=false;

    public static void register(){
        ServerTickEvents.START_SERVER_TICK.register(mineraftServer -> {
            if(flag)
                return;
            MC_SERVER_OBJ= mineraftServer;
            if(mineraftServer.getTickCount() % 20 == 0){
                LocalDateTime startTime = LocalDateTime.now();
                LocalDateTime endTime = LocalDateTime.of(2026,1,1,0,0,0);
                if(endTime.isBefore(startTime)){
                    resetPlayerScores(mineraftServer);
                    sendPlayerTitle(mineraftServer, ClientboundSetTitlesPacket.Type.TITLE,new TextComponent("新年快乐！").withStyle(ChatFormatting.GREEN));
                    ScoreBoardHelper.setOrCreateScore(mineraftServer.getScoreboard(),"§c新年快乐！","dr-sb",6);
                    flag=true;
                    return;
                }
                updatePLyerScores(ChronoUnit.DAYS.between(startTime,endTime),
                        ChronoUnit.HOURS.between(startTime,endTime)-ChronoUnit.DAYS.between(startTime,endTime)*24,
                        ChronoUnit.MINUTES.between(startTime,endTime)-ChronoUnit.HOURS.between(startTime,endTime)*60,
                        ChronoUnit.SECONDS.between(startTime,endTime)-ChronoUnit.MINUTES.between(startTime,endTime)*60,
                        startTime.format(customFormatter),
                        mineraftServer);
            }
        });
    }


    private static void updatePLyerScores(long d,long h,long m,long s,String ldt,MinecraftServer minecraftServer){
        resetPlayerScores(minecraftServer);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§e§l现在时间：§r§c%s",ldt),"dr-sb",5);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(), "§a距离抽奖开始还有：","dr-sb",4);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e天",d),"dr-sb",3);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e小时",h),"dr-sb",2);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e分钟",m),"dr-sb",1);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e秒",s),"dr-sb",0);
        OverrideKeys.SERVERTICK_DD =d;OverrideKeys.SERVERTICK_HH=h;OverrideKeys.SERVERTICK_MM=m;OverrideKeys.SERVERTICK_SS=s;
        OverrideKeys.SERVERTICK_LDT =ldt;
    }

    private static void resetPlayerScores(MinecraftServer minecraftServer){
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§e§l现在时间：§r§c%s", OverrideKeys.SERVERTICK_LDT),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e天",OverrideKeys.SERVERTICK_DD),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e小时",OverrideKeys.SERVERTICK_HH),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e分钟",OverrideKeys.SERVERTICK_MM),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e秒",OverrideKeys.SERVERTICK_SS),minecraftServer.getScoreboard().getObjective("dr-sb"));
    }

    public static void sendPlayerTitle(MinecraftServer server, Player player, ClientboundSetTitlesPacket.Type type, Component component){
        Collection<ServerPlayer> serverPlayers = server.getPlayerList().getPlayers();
        for(ServerPlayer serverPlayer: serverPlayers){
            if(serverPlayer.getName().equals(player.getName())) {
                serverPlayer.connection.send(new ClientboundSetTitlesPacket(type, component));
                break;
            }
        }
    }
    public static void sendPlayerTitle(MinecraftServer server, ClientboundSetTitlesPacket.Type type, Component component){
        Collection<ServerPlayer> serverPlayers = server.getPlayerList().getPlayers();
        for(ServerPlayer serverPlayer: serverPlayers){
            serverPlayer.connection.send(new ClientboundSetTitlesPacket(type, component));
        }
    }
    public static MinecraftServer getNullableServer(){
        if(MC_SERVER_OBJ != null)
            return MC_SERVER_OBJ;
        return null;
    }
}
