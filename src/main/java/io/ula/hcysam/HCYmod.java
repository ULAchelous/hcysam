package io.ula.hcysam;

import io.ula.hcysam.listeners.PlayerAttackListener;
import io.ula.hcysam.listeners.ServerStartedListener;
import io.ula.hcysam.listeners.ServerTickListener;
import net.fabricmc.api.ModInitializer;


public class HCYmod implements ModInitializer {
	public static final String MOD_ID = "hcyservermod";


	@Override
	public void onInitialize() {
        Registry_init.initialize();
        PlayerAttackListener.register();
        ServerTickListener.register();//Regist Server tick event
        ServerStartedListener.register();
        ServerStartedListener.register();
	}

}