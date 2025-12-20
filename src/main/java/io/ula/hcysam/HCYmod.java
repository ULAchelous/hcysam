package io.ula.hcysam;

import io.ula.hcysam.listeners.PlayerAttackListener;
import io.ula.hcysam.listeners.ServerLifecycleListener;
import io.ula.hcysam.listeners.ServerTickListener;
import io.ula.hcysam.server.ScoreBoardHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class HCYmod implements ModInitializer {
	public static final String MOD_ID = "hcyservermod";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        Registry_init.initialize();
        PlayerAttackListener.register();
        ServerLifecycleListener.register();
        ServerTickListener.register();
	}

}