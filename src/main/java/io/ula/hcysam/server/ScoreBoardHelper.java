package io.ula.hcysam.server;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.w3c.dom.Text;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardHelper {

    public static Objective getScoreboardObj(Scoreboard scoreboard, String name, MutableComponent displayname){
        Objective obj_1 = scoreboard.getObjective(name);
        if(obj_1 == null) {
            scoreboard
                    .addObjective(
                            name,
                            ObjectiveCriteria.DUMMY,
                            displayname,
                            ObjectiveCriteria.RenderType.INTEGER
                    );
        }
        return obj_1;
    }



    public static void setOrCreateScore(Scoreboard scoreboard,String playername,String objname,int sc){
        if(scoreboard.getObjective(objname) != null) {
            Score score = scoreboard.getOrCreatePlayerScore(playername, scoreboard.getObjective(objname));
            score.setScore(sc);
        }
    }
}
