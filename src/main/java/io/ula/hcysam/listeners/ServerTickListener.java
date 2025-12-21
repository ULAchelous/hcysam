package io.ula.hcysam.listeners;

import io.ula.hcysam.server.ScoreBoardHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class ServerTickListener {
    private static long dd=0,hh=0,mm=0,ss=0;
    private static String llddtt;
    private static MinecraftServer MC_SERVER_OBJ;
    private static DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void register(){
        ServerTickEvents.START_SERVER_TICK.register(mineraftServer -> {
            MC_SERVER_OBJ= mineraftServer;
            if(mineraftServer.getTickCount() % 20 == 0){
                LocalDateTime startTime = LocalDateTime.now();
                LocalDateTime endTime = LocalDateTime.of(2026,1,1,0,0,0);
                if(endTime.isBefore(startTime))
                    return;
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
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§a距离抽奖开始还有："),"dr-sb",4);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e天",d),"dr-sb",3);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e小时",h),"dr-sb",2);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e分钟",m),"dr-sb",1);
        ScoreBoardHelper.setOrCreateScore(minecraftServer.getScoreboard(),String.format("§c§l%d§r§e秒",s),"dr-sb",0);
        dd=d;hh=h;mm=m;ss=s;
        llddtt=ldt;
    }

    private static void resetPlayerScores(MinecraftServer minecraftServer){
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§e§l现在时间：§r§c%s", llddtt),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e天",dd),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e小时",hh),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e分钟",mm),minecraftServer.getScoreboard().getObjective("dr-sb"));
        minecraftServer.getScoreboard().resetPlayerScore(String.format("§c§l%d§r§e秒",ss),minecraftServer.getScoreboard().getObjective("dr-sb"));
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
