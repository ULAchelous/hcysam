package io.ula.hcysam.blocks;

import io.ula.hcysam.Registry_init;
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
import io.ula.hcysam.listeners.ServerTickListener;

public class Lottery_block extends Block {
    public Lottery_block(FabricBlockSettings settings){
        super(settings);
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack mainHandItem = player.getMainHandItem();
        if (!level.isClientSide) {
            if(player.isHolding(Registry_init.DELTA_TICKET)){
                if(mainHandItem.getCount() < 5)
                    return  InteractionResult.SUCCESS;
                //LOGGER.info(String.format("Delta_ticket:%d",mainHandItem.getCount()));
                MinecraftServer server = ServerTickListener.getNullableServer();
                if(server != null)
                    ServerTickListener.sendPlayerTitle(server, ClientboundSetTitlesPacket.Type.TITLE,new TextComponent("TEST").withStyle(ChatFormatting.GREEN));
                mainHandItem.setCount(mainHandItem.getCount()-5);
            }else{
                player.sendMessage(new TextComponent("用三角券！").withStyle(ChatFormatting.GREEN),player.getUUID());
            }
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.CONSUME;
        }
    }
}
