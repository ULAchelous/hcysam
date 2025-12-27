package io.ula.hcysam.blocks;

import io.ula.hcysam.OverrideKeys;
import io.ula.hcysam.lottery.LotteryEngine;
import io.ula.hcysam.lottery.LotteryResult;
import io.ula.hcysam.Registry_init;
import io.ula.hcysam.listeners.ServerTickListener;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.ula.hcysam.HCYmod.MOD_ID;


public class Lottery_block extends Block {
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public Lottery_block(FabricBlockSettings settings){
        super(settings);
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        OverrideKeys.updateKeys();
        ItemStack mainHandItem = player.getMainHandItem();
        LotteryResult lotteryResult = LotteryEngine.getResult(player);
        //LOGGER.info(lotteryResult.getName());
        if (!level.isClientSide) {
            if(player.isHolding(Registry_init.DELTA_TICKET)){
                if(mainHandItem.getCount() < 5)
                    return  InteractionResult.SUCCESS;
                //LOGGER.info(String.format("Delta_ticket:%d",mainHandItem.getCount()));
                MinecraftServer server = ServerTickListener.getNullableServer();
                mainHandItem.setCount(mainHandItem.getCount()-5);
                lotteryResult.serverBehavior(player,new TextComponent(""));//执行抽奖结果（服务端逻辑）
            }else{
                    ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(), ClientboundSetTitlesPacket.Type.ACTIONBAR,new TextComponent("用三角券！").withStyle(ChatFormatting.RED));
            }
            return InteractionResult.CONSUME;
        } else {
            lotteryResult.clientBehavior(player);
            return InteractionResult.CONSUME;
        }
    }
}
